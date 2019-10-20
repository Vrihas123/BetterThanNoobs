package com.in.out.hackathon.inoutapp.restapi;


import com.in.out.hackathon.inoutapp.models.BookingSpaceRequest;
import com.in.out.hackathon.inoutapp.models.BookingSpaceResponse;
import com.in.out.hackathon.inoutapp.models.NearbyParkingRequest;
import com.in.out.hackathon.inoutapp.models.NearbyParkingResponse;
import com.in.out.hackathon.inoutapp.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST(AppConstants.REQUEST_NEARBY_PARKING_PLACES)
    Call<NearbyParkingResponse> requestNearbyParkingPlace(@Body NearbyParkingRequest nearbyParkingRequest);

    @POST(AppConstants.REQUEST_BOOKING_SPACE_CREATION)
    Call<BookingSpaceResponse> requestBookingSpace(@Body BookingSpaceRequest bookingSpaceRequest);

}
