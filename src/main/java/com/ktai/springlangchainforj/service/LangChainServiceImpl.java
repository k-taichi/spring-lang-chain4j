package com.ktai.springlangchainforj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktai.springlangchainforj.dto.Person;
import com.ktai.springlangchainforj.prop.OpenAiApiProp;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import static dev.langchain4j.model.chat.request.ResponseFormatType.JSON;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * @author gmogshd taichi-kaneko
 */
@Service
public class LangChainServiceImpl implements LangChainService {

    private final OpenAiApiProp openAiApiProp;
    LangChainServiceImpl(OpenAiApiProp openAiApiProp){
        this.openAiApiProp = openAiApiProp;
    }
    public Person profileInvestigator(String body) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(openAiApiProp.getApiKey())
                .responseFormat("json_schema")
                .strictJsonSchema(true)
                .modelName(GPT_4_O_MINI)
                .build();
        ResponseFormat responseFormat = ResponseFormat.builder()
                .type(JSON) // type can be either TEXT (default) or JSON
                .jsonSchema(JsonSchema.builder()
                        .name("Person") // OpenAI requires specifying the name for the schema
                        .rootElement(JsonObjectSchema.builder() // see [1] below
                                .addStringProperty("name")
                                .addIntegerProperty("age")
                                .addNumberProperty("height")
                                .addStringProperty("specialty")
                                .addStringProperty("interest")
                                .addBooleanProperty("married")
                                .required("name", "age", "height", "specialty", "interest", "married") // see [2] below
                                .build())
                        .build())
                .build();

        UserMessage userMessage = UserMessage.from(body);

        ChatRequest chatRequest = ChatRequest.builder()
                .responseFormat(responseFormat)
                .messages(userMessage)
                .build();

        ChatResponse chatResponse = model.chat(chatRequest);

        String output = chatResponse.aiMessage().text();
        System.out.println(output); // {"name":"John","age":42,"height":1.75,"married":false}

        Person person = null;
        try {
            person = new ObjectMapper().readValue(output, Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(person); // Person[name=John, age=42, height=1.75, married=fal
        return person;
    }
}
