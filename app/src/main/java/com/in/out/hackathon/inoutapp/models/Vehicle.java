package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle implements Serializable {

    @SerializedName("two")
    private TwoWheeler twoWheeler;

    @SerializedName("four")
    private FourWheeler fourWheeler;

    public void setTwoWheeler(TwoWheeler twoWheeler) {
        this.twoWheeler = twoWheeler;
    }

    public void setFourWheeler(FourWheeler fourWheeler) {
        this.fourWheeler = fourWheeler;
    }
}
