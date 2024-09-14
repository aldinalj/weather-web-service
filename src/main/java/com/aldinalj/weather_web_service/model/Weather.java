package com.aldinalj.weather_web_service.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Weather {

    @JsonProperty("data")
    private List<Observation> data;


    public List<Observation> getData() {
        return data;
    }

    public void setData(List<Observation> data) {
        this.data = data;
    }
    }
