package com.in.out.hackathon.inoutapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.out.hackathon.inoutapp.R;


public class DetailParkingFragment extends Fragment {

    public DetailParkingFragment() {
        // Required empty public constructor
    }

    public static DetailParkingFragment newInstance(String param1, String param2) {
        DetailParkingFragment fragment = new DetailParkingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_detail_parking, container, false);
    }


}
