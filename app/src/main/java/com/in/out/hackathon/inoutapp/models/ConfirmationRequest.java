package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfirmationRequest implements Serializable {

    @SerializedName("id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }
}
