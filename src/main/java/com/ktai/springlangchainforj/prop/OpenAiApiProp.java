package com.ktai.springlangchainforj.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gmogshd taichi-kaneko
 */
@Data
@Component
@ConfigurationProperties(prefix = "openai")
public class OpenAiApiProp {
    String apiKey;
    //String token;
    //String model;
    //String maxTokens;
    //String temperature;
}
