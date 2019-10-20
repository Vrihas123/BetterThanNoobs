package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParkingPlaceData implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
