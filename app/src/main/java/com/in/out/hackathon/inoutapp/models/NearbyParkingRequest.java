package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NearbyParkingRequest implements Serializable {

    @SerializedName("to_time")
    private String toTime;

    @SerializedName("from_time")
    private String fromTime;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("two")
    private int two;

    @SerializedName("four")
    private int four;

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public void setFour(int four) {
        this.four = four;
    }
}
