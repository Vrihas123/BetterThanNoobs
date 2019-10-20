package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfirmBookingRequest implements Serializable {

    @SerializedName("user_id")
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
