package com.ktai.springlangchainforj;

import com.ktai.springlangchainforj.dto.WeatherInfo;
import com.ktai.springlangchainforj.prop.OpenAiApiProp;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@SpringBootTest
class WeatherTests {

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
        WeatherForecast weatherForecast = AiServices.create(WeatherForecast.class, model);
        WeatherInfo info = weatherForecast.getWeatherInfo("明日(2024/12/19)の天気は曇りです");
        System.out.println(info);
        //WeatherInfo[date=2024-12-19, weather=曇り]

    }

}
