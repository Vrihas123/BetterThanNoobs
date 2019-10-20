package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParkingPlaceData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("from_time")
    private String fromTime;

    @SerializedName("to_time")
    private String toTime;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private String status;

    @Override
    public String toString() {
        return "ParkingPlaceData{" +
                "id=" + id +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
