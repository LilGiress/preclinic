package com.medecineWebApp.Configuration.models;

import java.util.List;

public class Region {
    private String region;
    private List<City> cities;

    // Getters and Setters
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
