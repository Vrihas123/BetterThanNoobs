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

    @SerializedName("additional_information")
    private String additionalInformation;

    @SerializedName("location")
    private LocationData location;

    @SerializedName("vehicle")
    private VehicleData vehicle;



    private Integer bikeAvailable;
    private Integer carAvailable;

    private Float bikeCharge;
    private Float carCharge;

    public Float getBikeCharge() {
        return bikeCharge;
    }

    public void setBikeCharge(Float bikeCharge) {
        this.bikeCharge = bikeCharge;
    }

    public Float getCarCharge() {
        return carCharge;
    }

    public void setCarCharge(Float carCharge) {
        this.carCharge = carCharge;
    }

    public Integer getBikeAvailable() {
        return bikeAvailable;
    }

    public void setBikeAvailable(Integer bikeAvailable) {
        this.bikeAvailable = bikeAvailable;
    }

    public Integer getCarAvailable() {
        return carAvailable;
    }

    public void setCarAvailable(Integer carAvailable) {
        this.carAvailable = carAvailable;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public LocationData getLocation() {
        return location;
    }

    public void setLocation(LocationData location) {
        this.location = location;
    }

    public VehicleData getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleData vehicle) {
        this.vehicle = vehicle;
    }

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
