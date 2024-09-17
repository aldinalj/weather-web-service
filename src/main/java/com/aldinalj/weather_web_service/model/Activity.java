package com.aldinalj.weather_web_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer code;
    @Size(max = 1000)
    private String description;

    @JsonProperty("temp_min")
    @Column(name = "temperature_min")
    private Integer temperatureMin;

    @JsonProperty("temp_max")
    @Column(name = "temperature_max")
    private Integer temperatureMax;

    @JsonProperty("price_min")
    @Column(name = "price_min")
    private Integer priceMin;

    @JsonProperty("price_max")
    @Column(name = "price_max")
    private Integer priceMax;




    public Activity() {}

    public Activity(String name, Integer code, String description, Integer temperatureMin, Integer temperatureMax, Integer priceMin, Integer priceMax) {
        this.name = name;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
