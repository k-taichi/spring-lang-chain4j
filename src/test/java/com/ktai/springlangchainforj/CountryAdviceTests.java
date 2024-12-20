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
class CountryAdviceTests {

    @Autowired
    private OpenAiApiProp openAiApiProp;

    @Test
    void weatherTest() {
        String apiKey = openAiApiProp.getApiKey();
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(GPT_4_O_MINI)
                .build();
        CountryAdvice countryAdvice = AiServices.create(CountryAdvice.class, model);
        String info = countryAdvice.chat("首都", "中国");
        System.out.println(info);

    }

}
