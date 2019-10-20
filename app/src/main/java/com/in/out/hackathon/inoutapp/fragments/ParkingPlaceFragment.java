package com.in.out.hackathon.inoutapp.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.activities.MainActivity;
import com.in.out.hackathon.inoutapp.models.NearbyParkingRequest;
import com.in.out.hackathon.inoutapp.models.NearbyParkingResponse;
import com.in.out.hackathon.inoutapp.restapi.ApiServices;
import com.in.out.hackathon.inoutapp.restapi.AppClient;
import com.in.out.hackathon.inoutapp.utils.AppConstants;
import com.in.out.hackathon.inoutapp.utils.NetworkUtils;
import com.in.out.hackathon.inoutapp.utils.ProgressDialog;
import com.in.out.hackathon.inoutapp.utils.SharedPrefs;

import java.util.Arrays;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ParkingPlaceFragment extends Fragment implements View.OnClickListener{

    private SharedPrefs sharedPrefs;
    private TextView txtPlace, txtFromDate, txtToDate, txtFromTime, txtToTime;
    private LinearLayout llFromDate, llToDate, llFromTime, llToTime;
    private TextInputEditText et4Quantity, et2Quantity;
    private Button btnSearch;
    private DatePickerDialog datePickerDialogFrom, datePickerDialogTo;
    private String four, two;
    private ProgressDialog progressDialog;
    Gson gson;

    public ParkingPlaceFragment() {
        // Required empty public constructor
    }

    public static ParkingPlaceFragment newInstance() {
        ParkingPlaceFragment fragment = new ParkingPlaceFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parking_place, container, false);
        sharedPrefs = new SharedPrefs(getContext());
        initialize(view);
        gson = new Gson();
        progressDialog = new ProgressDialog();
        btnSearch.setOnClickListener(this);
        llFromDate.setOnClickListener(this);
        llToDate.setOnClickListener(this);
        llFromTime.setOnClickListener(this);
        llToTime.setOnClickListener(this);
        String apiKey = getString(R.string.api_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), apiKey);
        }

        PlacesClient placesClient = Places.createClient(getActivity());


        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("SelectedPlace", "Place: " + place.getName() + ", " + place.getId());
                sharedPrefs.setLatitude((float)place.getLatLng().latitude);
                sharedPrefs.setLongitude((float)place.getLatLng().longitude);
//                Toast.makeText(getContext(), place.getLatLng().toString(), Toast.LENGTH_LONG).show();
                Log.i("lat", " " + place.getLatLng().latitude);
                Log.i("lng", " " + place.getLatLng().longitude);
                txtPlace.setText(place.getName());

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("SelectedPlace", "An error occurred: " + status);
            }
        });
        return view;
    }


    private void initialize(View view) {
        txtPlace = view.findViewById(R.id.txt_place);
        txtFromDate = view.findViewById(R.id.txt_from_date);
        txtToDate = view.findViewById(R.id.txt_to_date);
        txtFromTime = view.findViewById(R.id.txt_from_time);
        txtToTime = view.findViewById(R.id.txt_to_time);
        llFromDate = view.findViewById(R.id.ll_from_date);
        llToDate = view.findViewById(R.id.ll_to_date);
        llFromTime = view.findViewById(R.id.ll_from_time);
        llToTime = view.findViewById(R.id.ll_to_time);
        et4Quantity = view.findViewById(R.id.et_4_wheeler_quantity);
        et2Quantity = view.findViewById(R.id.et_2_wheeler_quantity);
        btnSearch = view.findViewById(R.id.btn_search);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_from_date :
                showFromDatePicker();
                break;
            case R.id.ll_to_date :
                showToDatePicker();
                break;
            case R.id.ll_from_time :
                showFromTimePicker();
                break;
            case R.id.ll_to_time :
                showToTimePicker();
                break;
            case R.id.btn_search :
                sendDetails();
                break;
        }
    }

    private void showFromDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        int cMonth = calendar.get(Calendar.MONTH);
        int cYear = calendar.get(Calendar.YEAR);
        datePickerDialogFrom = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearOfCentury, int monthOfYear, int dayOfMonth) {
                txtFromDate.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + yearOfCentury);
            }
        }, cYear, cMonth, cDay);
        datePickerDialogFrom.show();
    }
    private void showToDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        int cMonth = calendar.get(Calendar.MONTH);
        int cYear = calendar.get(Calendar.YEAR);
        datePickerDialogTo = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearOfCentury, int monthOfYear, int dayOfMonth) {
                txtToDate.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + yearOfCentury);
            }
        }, cYear, cMonth, cDay);
        datePickerDialogTo.show();

    }
    private void showFromTimePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txtFromTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select From Time");
        mTimePicker.show();

    }
    private void showToTimePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txtToTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select To Time");
        mTimePicker.show();
    }

    private void sendDetails() {
        four = et4Quantity.getText().toString().trim();
        two = et2Quantity.getText().toString().trim();
        if (four.isEmpty() || two.isEmpty()) {
            Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }else {
            apiCall();
        }
    }

    private void apiCall() {
        progressDialog.showDialog("Getting neraby parking places...", getContext());
        NearbyParkingRequest parkingRequest = new NearbyParkingRequest();
        parkingRequest.setLatitude(sharedPrefs.getLatitude());
        parkingRequest.setLongitude(sharedPrefs.getLongitude());
        parkingRequest.setFour(Integer.parseInt(four));
        parkingRequest.setTwo(Integer.parseInt(two));
        parkingRequest.setFromTime("2019-10-19T18:57:11.647130");
        parkingRequest.setToTime("2019-10-19T18:57:47.358744");
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<NearbyParkingResponse> call = apiServices.requestNearbyParkingPlace(parkingRequest);
        call.enqueue(new Callback<NearbyParkingResponse>() {
            @Override
            public void onResponse(Call<NearbyParkingResponse> call, Response<NearbyParkingResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    Log.i("res", " " + response.body().getParkingPlaceDataList().get(0));

                    sharedPrefs.setNearbyParking(gson.toJson(response.body(), NearbyParkingResponse.class));
                    AvailableParkingFragment availableParkingFragment = AvailableParkingFragment.newInstance();
                    ((MainActivity)getContext()).createFragment(availableParkingFragment, "Available Parking Fragment", true);
                }
            }

            @Override
            public void onFailure(Call<NearbyParkingResponse> call, Throwable t) {
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
