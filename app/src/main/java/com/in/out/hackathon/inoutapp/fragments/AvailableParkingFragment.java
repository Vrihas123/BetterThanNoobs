package com.in.out.hackathon.inoutapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.in.out.hackathon.inoutapp.R;


public class AvailableParkingFragment extends Fragment implements View.OnClickListener{

    private ImageView ivNavigateBack;
    private RecyclerView rvAvailableParking;

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
