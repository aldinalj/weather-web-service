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
    private double temperatureMax;

    @JsonProperty("price_min")
    @Column(name = "price_min")
    private double priceMin;

    @JsonProperty("price_max")
    @Column(name = "price_max")
    private double priceMax;




    public Activity() {}

    public Activity(String name, Integer code, String description, Integer temperatureMin, double temperatureMax, double priceMin, double priceMax) {
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

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }
}
