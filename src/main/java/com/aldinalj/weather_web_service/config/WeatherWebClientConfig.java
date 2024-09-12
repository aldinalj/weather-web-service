package com.aldinalj.weather_web_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WeatherWebClientConfig {

    @Bean
    public WebClient.Builder weatherWebClientBuilder() {

        return WebClient.builder();
    }
}
