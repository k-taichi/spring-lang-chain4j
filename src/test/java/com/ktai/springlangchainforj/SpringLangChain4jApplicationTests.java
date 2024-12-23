package com.ktai.springlangchainforj;

import com.ktai.springlangchainforj.prop.OpenAiApiProp;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@SpringBootTest
class SpringLangChain4jApplicationTests {

    @Autowired
    private OpenAiApiProp openAiApiProp;

    @Test
    void openAiApiConnectionTest() {
        String apiKey = openAiApiProp.getApiKey();
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(GPT_3_5_TURBO)
                .build();
        String answer = model.generate("こんにちは");
        System.out.println(answer);
// こんにちは！何かお手伝いできることはありますか？

    }

}
