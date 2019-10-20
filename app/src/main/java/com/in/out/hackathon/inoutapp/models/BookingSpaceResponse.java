package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingSpaceResponse implements Serializable {

    @SerializedName("success")
    private boolean success;

    public boolean isSuccess() {
        return success;
    }
}
