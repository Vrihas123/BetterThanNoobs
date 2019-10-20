package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FourWheeler implements Serializable {

    @SerializedName("charge")
    private float charge;

    @SerializedName("quantity")
    private int quantity;

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
