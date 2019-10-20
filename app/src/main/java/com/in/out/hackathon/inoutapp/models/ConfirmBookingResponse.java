package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ConfirmBookingResponse implements Serializable {

    @SerializedName("data")
    private List<ParkingPlaceData> parkingPlaceDataList;

    public List<ParkingPlaceData> getParkingPlaceDataList() {
        return parkingPlaceDataList;
    }
}
