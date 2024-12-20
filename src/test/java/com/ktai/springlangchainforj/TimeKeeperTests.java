package com.ktai.springlangchainforj;

import com.ktai.springlangchainforj.dto.WeatherInfo;
import com.ktai.springlangchainforj.prop.OpenAiApiProp;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@SpringBootTest
class TimeKeeperTests {

    @Autowired
    private OpenAiApiProp openAiApiProp;

    @Test
    void weatherTest() {
        String apiKey = openAiApiProp.getApiKey();
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .responseFormat("json_schema")
                .strictJsonSchema(true)
                .modelName(GPT_4_O_MINI)
                .build();

        TimeKeeper timeKeeper = AiServices.builder(TimeKeeper.class)
                .chatLanguageModel(model)
                .tools(new Clock())
                .build();
        String answer = timeKeeper.ask("今何時？");
        System.out.println(answer);
        //現在の時刻は22時08分です。

    }

}
