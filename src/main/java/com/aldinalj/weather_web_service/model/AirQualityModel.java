package com.aldinalj.weather_web_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirQualityModel {


    private int aqi;
    private double o3;
    private double so2;
    private double no2;
    private double co;
    private double pm25;
    private double pm10;

    @JsonProperty("pollen_level_tree")
    private int pollenLevelTree;

    @JsonProperty("pollen_level_grass")
    private int pollenLevelGrass;

    @JsonProperty("pollen_level_weed")
    private int pollenLevelWeed;

    @JsonProperty("mold_level")
    private int moldLevel;

    @JsonProperty("predominant_pollen_type")
    private String predominantPollenType;

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public int getPollenLevelTree() {
        return pollenLevelTree;
    }

    public void setPollenLevelTree(int pollenLevelTree) {
        this.pollenLevelTree = pollenLevelTree;
    }

    public int getPollenLevelGrass() {
        return pollenLevelGrass;
    }

    public void setPollenLevelGrass(int pollenLevelGrass) {
        this.pollenLevelGrass = pollenLevelGrass;
    }

    public int getPollenLevelWeed() {
        return pollenLevelWeed;
    }

    public void setPollenLevelWeed(int pollenLevelWeed) {
        this.pollenLevelWeed = pollenLevelWeed;
    }

    public int getMoldLevel() {
        return moldLevel;
    }

    public void setMoldLevel(int moldLevel) {
        this.moldLevel = moldLevel;
    }

    public String getPredominantPollenType() {
        return predominantPollenType;
    }

    public void setPredominantPollenType(String predominantPollenType) {
        this.predominantPollenType = predominantPollenType;
    }
}
