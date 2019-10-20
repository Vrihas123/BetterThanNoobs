package com.in.out.hackathon.inoutapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ConfirmBookingResponse implements Serializable {

    @SerializedName("confirm_booking_list")
    private List<ConfirmBookingData> confirmBookingDataList;

    public List<ConfirmBookingData> getConfirmBookingDataList() {
        return confirmBookingDataList;
    }
}
