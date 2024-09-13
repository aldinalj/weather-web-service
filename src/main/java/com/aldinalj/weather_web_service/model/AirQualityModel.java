package com.aldinalj.weather_web_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirQualityModel {

    @JsonProperty("pollen_level_tree")
    private int pollenLevelTree;

    @JsonProperty("pollen_level_grass")
    private int pollenLevelGrass;

    @JsonProperty("pollen_level_weed")
    private int pollenLevelWeed;

    @JsonProperty("predominant_pollen_type")
    private String predominantPollenType;


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

    public String getPredominantPollenType() {
        return predominantPollenType;
    }

    public void setPredominantPollenType(String predominantPollenType) {
        this.predominantPollenType = predominantPollenType;
    }
}
