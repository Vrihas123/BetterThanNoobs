package com.in.out.hackathon.inoutapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.adapter.AvailableParkingAdapter;
import com.in.out.hackathon.inoutapp.models.NearbyParkingResponse;
import com.in.out.hackathon.inoutapp.utils.SharedPrefs;


public class AvailableParkingFragment extends Fragment implements View.OnClickListener{

    private ImageView ivNavigateBack;
    private RecyclerView rvAvailableParking;
    private AvailableParkingAdapter availableParkingAdapter;
    private SharedPrefs sharedPrefs;
    private Gson gson;

    public AvailableParkingFragment() {
        // Required empty public constructor
    }

    public static AvailableParkingFragment newInstance() {
        AvailableParkingFragment fragment = new AvailableParkingFragment();
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
        View view = inflater.inflate(R.layout.fragment_available_parking, container, false);
        initView(view);
        Gson gson = new Gson();
        sharedPrefs = new SharedPrefs(getContext());
        availableParkingAdapter = new AvailableParkingAdapter(getContext());
        rvAvailableParking.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAvailableParking.setAdapter(availableParkingAdapter);
        System.out.println("------"+gson.fromJson(sharedPrefs.getNearbyParking(), NearbyParkingResponse.class).getParkingPlaceDataList().size());
        availableParkingAdapter.setParkingPlaceDataList(gson.fromJson(sharedPrefs.getNearbyParking(), NearbyParkingResponse.class).getParkingPlaceDataList());
        availableParkingAdapter.notifyDataSetChanged();
        return view;
    }

    private void initView(View view){
        ivNavigateBack = view.findViewById(R.id.iv_back);
        rvAvailableParking = view.findViewById(R.id.rv_parkings);

        ivNavigateBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                //Go Back
                break;
        }
    }
}
