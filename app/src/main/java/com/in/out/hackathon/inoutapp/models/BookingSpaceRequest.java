package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingSpaceRequest implements Serializable {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("name")
    private String bookingSpaceName;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private  float longitude;

    @SerializedName("from_time")
    private String fromTime;

    @SerializedName("to_time")
    private String toTime;

    @SerializedName("image")
    private String image;

    @SerializedName("vehicle")
    private Vehicle vehicle;

    public void setBookingSpaceName(String bookingSpaceName) {
        this.bookingSpaceName = bookingSpaceName;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
