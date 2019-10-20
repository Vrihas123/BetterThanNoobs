package com.in.out.hackathon.inoutapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.adapter.ConfirmBookingAdapter;
import com.in.out.hackathon.inoutapp.models.ConfirmBookingRequest;
import com.in.out.hackathon.inoutapp.models.ConfirmBookingResponse;
import com.in.out.hackathon.inoutapp.restapi.ApiServices;
import com.in.out.hackathon.inoutapp.restapi.AppClient;
import com.in.out.hackathon.inoutapp.utils.NetworkUtils;
import com.in.out.hackathon.inoutapp.utils.ProgressDialog;
import com.in.out.hackathon.inoutapp.utils.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConfirmBookingFragment extends Fragment {

    private ConfirmBookingAdapter adapter;
    private RecyclerView rvConfirmBooking;
    private SharedPrefs sharedPrefs;
    private ProgressDialog progressDialog;

    public ConfirmBookingFragment() {
        // Required empty public constructor
    }

    public static ConfirmBookingFragment newInstance() {
        ConfirmBookingFragment fragment = new ConfirmBookingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_booking, container, false);
        adapter = new ConfirmBookingAdapter(getContext());
        sharedPrefs = new SharedPrefs(getContext());
        progressDialog = new ProgressDialog();
        initialise(view);
        rvConfirmBooking.setLayoutManager(new LinearLayoutManager(getContext()));
        rvConfirmBooking.setAdapter(adapter);
        apiCall();
        return view;
    }

    private void initialise(View view) {
        rvConfirmBooking = view.findViewById(R.id.rv_confirm_booking);
    }

    private void apiCall() {
        progressDialog.showDialog("Fetching the applications...", getContext());
        ConfirmBookingRequest confirmBookingRequest = new ConfirmBookingRequest();
        confirmBookingRequest.setUserId(1);
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<ConfirmBookingResponse> call = apiServices.requestConfirmBooking(confirmBookingRequest);
        call.enqueue(new Callback<ConfirmBookingResponse>() {
            @Override
            public void onResponse(Call<ConfirmBookingResponse> call, Response<ConfirmBookingResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    ConfirmBookingResponse confirmBookingResponse = response.body();
                    adapter.setParkingPlaceDataList(confirmBookingResponse.getParkingPlaceDataList());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ConfirmBookingResponse> call, Throwable t) {
                progressDialog.hideDialog();
                if (getContext() != null) {
                    if (!NetworkUtils.isNetworkAvailable(getContext())) {
                        Toast.makeText(getContext(), "No internet connection. Please try again.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e("error--", "server error");
                        Toast.makeText(getContext(), "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
