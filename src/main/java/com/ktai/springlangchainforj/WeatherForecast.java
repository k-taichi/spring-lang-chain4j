package com.ktai.springlangchainforj;

import com.ktai.springlangchainforj.dto.WeatherInfo;
import dev.langchain4j.service.SystemMessage;

/**
 * @author gmogshd taichi-kaneko
 */
public interface WeatherForecast {
    @SystemMessage("天気情報を取得します")
    WeatherInfo getWeatherInfo(String text);

}
