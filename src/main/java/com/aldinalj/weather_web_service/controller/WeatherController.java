package com.aldinalj.weather_web_service.controller;

import com.aldinalj.weather_web_service.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WebClient weatherWebClientConfig;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherController (WebClient.Builder webClient) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.weatherbit.io/v2.0/current")
                .build();
    }

    @GetMapping("/today")
    public Mono<Weather> getWeatherForToday() {

        return weatherWebClientConfig.get()
                .uri(weather -> weather
                        .queryParam("key", apiKey)
                        .queryParam("city", "Stockholm")
                        .build()
                )
                .retrieve()
                .bodyToMono(Weather.class);
    }


}

