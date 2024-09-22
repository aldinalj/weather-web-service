package com.aldinalj.weather_web_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotNull
    @JsonProperty("weather_code")
    private Integer weatherCode;

    @Size(max = 1000, message = "Description can only be a maximum of 1000 characters")
    private String description;

    @JsonProperty("temp_min")
    @Column(name = "temperature_min")
    @Min(value = -100, message = "Invalid minimum temperature")
    @Max(value = 50, message = "Invalid maximum temperature")
    private Integer temperatureMin;

    @JsonProperty("temp_max")
    @Column(name = "temperature_max")
    @Min(value = -100, message = "Invalid minimum temperature")
    @Max(value = 50, message = "Invalid maximum temperature")
    private Integer temperatureMax;

    @JsonProperty("price_min")
    @Column(name = "price_min")
    @Min(value = 0, message = "Minimum price cannot be less than 0")
    @Max(value = 5000, message = "Minimum price cannot be more than 5000")
    private Integer priceMin;

    @JsonProperty("price_max")
    @Column(name = "price_max")
    @Min(value = 0, message = "Maximum price cannot be less than 0")
    @Max(value = 5000, message = "Maximum price cannot be more than 5000")
    private Integer priceMax;

    public Activity() {}

    public Activity(String name, Integer weatherCode, String description, Integer temperatureMin, Integer temperatureMax, Integer priceMin, Integer priceMax) {
        this.name = name;
        this.weatherCode = weatherCode;
        this.description = description;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Integer temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Integer temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }
}
