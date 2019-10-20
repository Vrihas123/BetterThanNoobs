package com.in.out.hackathon.inoutapp.restapi;


import com.in.out.hackathon.inoutapp.models.BookingSpaceRequest;
import com.in.out.hackathon.inoutapp.models.BookingSpaceResponse;
import com.in.out.hackathon.inoutapp.models.ConfirmBookingRequest;
import com.in.out.hackathon.inoutapp.models.ConfirmBookingResponse;
import com.in.out.hackathon.inoutapp.models.ConfirmationRequest;
import com.in.out.hackathon.inoutapp.models.ConfirmationResponse;
import com.in.out.hackathon.inoutapp.models.NearbyParkingRequest;
import com.in.out.hackathon.inoutapp.models.NearbyParkingResponse;
import com.in.out.hackathon.inoutapp.utils.AppConstants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {

    @POST(AppConstants.REQUEST_NEARBY_PARKING_PLACES)
    Call<NearbyParkingResponse> requestNearbyParkingPlace(@Body NearbyParkingRequest nearbyParkingRequest);

    @Multipart
    @POST(AppConstants.REQUEST_BOOKING_SPACE_CREATION)
    Call<BookingSpaceResponse> requestBookingSpace(
            @Part("data") RequestBody description,
            @Part MultipartBody.Part file);

    @POST(AppConstants.REQUEST_CONFIRM_BOOKING)
    Call<ConfirmBookingResponse> requestConfirmBooking(@Body ConfirmBookingRequest confirmBookingRequest);

    @POST(AppConstants.REQUEST_ACCEPT)
    Call<ConfirmationResponse> requestAccept(@Body ConfirmationRequest confirmationRequest);

    @POST(AppConstants.REQUEST_DENY)
    Call<ConfirmationResponse> requestDeny(@Body ConfirmationRequest confirmationRequest);

}
