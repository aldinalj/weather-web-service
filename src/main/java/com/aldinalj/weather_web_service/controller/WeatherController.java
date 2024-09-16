package com.aldinalj.weather_web_service.controller;

import com.aldinalj.weather_web_service.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/current")
@RestController
public class WeatherController {

    private final WebClient weatherWebClientConfig;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherController (WebClient.Builder webClient) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.weatherbit.io/v2.0/current")
                .build();
    }

    @GetMapping("/weather")
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

