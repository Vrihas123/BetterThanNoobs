package com.in.out.hackathon.inoutapp.fragments;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.models.ParkingPlaceData;
import com.in.out.hackathon.inoutapp.utils.SharedPrefs;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class DetailParkingFragment extends Fragment implements View.OnClickListener{

    private ImageView ivParkingSpace;
    private TextView tvName, tvLocation, tvCharges, tvBikeAvailability, tvCarAvailability, tvDescription;
    private Button btnCall, btnGetDirections, btnBook;
    private Gson gson;
    private ParkingPlaceData data;
    private SharedPrefs sharedPrefs;

    public DetailParkingFragment() {
        // Required empty public constructor
    }

    public static DetailParkingFragment newInstance() {
        DetailParkingFragment fragment = new DetailParkingFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail_parking, container, false);

        gson = new Gson();
        sharedPrefs = new SharedPrefs(getContext());
        initView(view);
        try {
            setData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void initView(View view){
        ivParkingSpace = view.findViewById(R.id.iv_parking_space);

        tvName = view.findViewById(R.id.tv_parking_name);
        tvLocation = view.findViewById(R.id.tv_location);
        tvCharges = view.findViewById(R.id.tv_charges);
        tvCarAvailability = view.findViewById(R.id.tv_car_availability);
        tvBikeAvailability = view.findViewById(R.id.tv_bike_availability);
        tvDescription = view.findViewById(R.id.tv_description);

        btnCall = view.findViewById(R.id.btn_call);
        btnGetDirections = view.findViewById(R.id.btn_directions);
        btnBook = view.findViewById(R.id.btn_book);

        btnBook.setOnClickListener(this);
        btnGetDirections.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    void setData() throws IOException {
        Float charge = 0.0f;
        data = gson.fromJson(sharedPrefs.getSelectedParking(), ParkingPlaceData.class);
        tvName.setText(data.getName());
        tvLocation.setText(getAddress(data.getLocation().getLat(), data.getLocation().getLon()));
        tvBikeAvailability.setText(data.getBikeAvailable()+"");
        tvCarAvailability.setText(data.getCarAvailable()+"");
        if(data.getBikeAvailable()>=sharedPrefs.getRequiredBike()){
            charge = sharedPrefs.getRequiredBike()*data.getBikeCharge();
        }
        else{
            charge = data.getBikeAvailable()*data.getBikeCharge();
        }

        if(data.getCarAvailable()>=sharedPrefs.getRequiredCar()){
            charge = sharedPrefs.getRequiredCar()*data.getCarCharge();
        }
        else{
            charge = data.getCarAvailable()*data.getCarCharge();
        }

    }
    private String getAddress(Float lat, Float lon) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        addresses = geocoder.getFromLocation(lat, lon, 1);
        return addresses.get(0).getAddressLine(0);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_call){
            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "+91-8963833132"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getContext().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }
                getContext().startActivity(callIntent);
            } catch (ActivityNotFoundException activityException) {
                Log.e("Calling a Phone Number", "Call failed", activityException);
            }
        }
        else if(v.getId() == R.id.btn_directions){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=" + Float.toString(data.getLocation().getLat()) + ", " + Float.toString(data.getLocation().getLon())));
            startActivity(intent);
        }
        else if(v.getId() == R.id.btn_book){

        }
    }
}
