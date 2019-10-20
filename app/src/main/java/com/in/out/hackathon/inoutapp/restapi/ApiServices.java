package com.in.out.hackathon.inoutapp.restapi;


import com.in.out.hackathon.inoutapp.models.NearbyParkingRequest;
import com.in.out.hackathon.inoutapp.models.NearbyParkingResponse;
import com.in.out.hackathon.inoutapp.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST(AppConstants.REQUEST_NEARBY_PARKING_PLACES)
    Call<NearbyParkingResponse> requestNearbyParkingPlace(@Body NearbyParkingRequest nearbyParkingRequest);

}
