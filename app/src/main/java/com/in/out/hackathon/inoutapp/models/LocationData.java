package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationData implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("latitude")
    private Float lat;

    @SerializedName("longitude")
    private Float lon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "id='" + id + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
