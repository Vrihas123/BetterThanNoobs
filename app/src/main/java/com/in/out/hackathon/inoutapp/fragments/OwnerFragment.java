package com.in.out.hackathon.inoutapp.fragments;


import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.in.out.hackathon.inoutapp.models.BookingSpaceRequest;
import com.in.out.hackathon.inoutapp.models.BookingSpaceResponse;
import com.in.out.hackathon.inoutapp.models.FourWheeler;
import com.in.out.hackathon.inoutapp.models.TwoWheeler;
import com.in.out.hackathon.inoutapp.models.Vehicle;
import com.in.out.hackathon.inoutapp.restapi.ApiServices;
import com.in.out.hackathon.inoutapp.restapi.AppClient;
import com.in.out.hackathon.inoutapp.utils.NetworkUtils;
import com.in.out.hackathon.inoutapp.utils.ProgressDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class OwnerFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText etBookSpaceName, et4Q, et2Q, et4C, et2C;
    private TextView txtFromTimeBook, txtToTimeBook;
    private LinearLayout llFromTimeBook, llToTimeBook;
    private float latitude, longitude;
    private String q4, q2, c4, c2, name;
    private ProgressDialog progressDialog;
    private ImageView ivPic;
    private Button btnPic, btnCreateBookingSpace;
    private static int RESULT_LOAD_IMAGE = 1;
    private String imageString;
    private Uri selectedImage;
    private String picturePath;
    private Gson gson = new Gson();

    public OwnerFragment() {
        // Required empty public constructor
    }

    public static OwnerFragment newInstance() {
        OwnerFragment fragment = new OwnerFragment();
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
        View view = inflater.inflate(R.layout.fragment_owner, container, false);
        initialise(view);
        progressDialog = new ProgressDialog();
        btnPic.setOnClickListener(this);
        btnCreateBookingSpace.setOnClickListener(this);
        llFromTimeBook.setOnClickListener(this);
        llToTimeBook.setOnClickListener(this);
        String apiKey = getString(R.string.api_key);

        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), apiKey);
        }

        PlacesClient placesClient = Places.createClient(getActivity());


        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment_booking_space);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("SelectedPlace", "Place: " + place.getName() + ", " + place.getId());
                latitude = (float)place.getLatLng().latitude;
                longitude = (float)place.getLatLng().longitude;
//                Toast.makeText(getContext(), place.getLatLng().toString(), Toast.LENGTH_LONG).show();
                Log.i("lat", " " + place.getLatLng().latitude);
                Log.i("lng", " " + place.getLatLng().longitude);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("SelectedPlace", "An error occurred: " + status);
            }
        });



        return view;
    }

    private void initialise(View view) {
        etBookSpaceName = view.findViewById(R.id.et_booking_space_name);
        et4Q = view.findViewById(R.id.et_quantity_4_wheeler);
        et2Q = view.findViewById(R.id.et_quantity_2_wheeler);
        et4C = view.findViewById(R.id.et_price_4_wheeler);
        et2C = view.findViewById(R.id.et_price_2_wheeler);
        llFromTimeBook = view.findViewById(R.id.ll_from_time_book);
        llToTimeBook = view.findViewById(R.id.ll_to_time_book);
        txtFromTimeBook = view.findViewById(R.id.txt_from_time_book);
        txtToTimeBook = view.findViewById(R.id.txt_to_time_book);
        btnPic = view.findViewById(R.id.btn_pic);
        btnCreateBookingSpace = view.findViewById(R.id.btn_create_booking_space);
        ivPic = view.findViewById(R.id.iv_pic);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_from_time_book :
                showFromTimePicker();
                break;
            case R.id.ll_to_time_book :
                showToTimePicker();
                break;
            case R.id.btn_pic :
                uploadPic();
                break;
            case R.id.btn_create_booking_space :
                sendDetails();
                break;
        }
    }

    private void showFromTimePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                txtFromTimeBook.setText( selectedHour + ":" + selectedMinute);
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
                txtToTimeBook.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select To Time");
        mTimePicker.show();
    }


    private void sendDetails() {
        q4 = et4Q.getText().toString().trim();
        q2 = et2Q.getText().toString().trim();
        c4 = et4C.getText().toString().trim();
        c2 = et2C.getText().toString().trim();
        name = etBookSpaceName.getText().toString().trim();

        if (name.isEmpty() || q4.isEmpty() || q2.isEmpty() || c4.isEmpty() || c2.isEmpty()) {
            Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }else {
            apiCall();
        }
    }

    private void uploadPic() {

        try {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_LOAD_IMAGE);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            ivPic.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            imageString = Base64.encodeToString(imageBytes, 0);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        } else {
            //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
            Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_LONG).show();
        }
    }

    private void apiCall() {
        progressDialog.showDialog("Creating your booking space", getContext());
        BookingSpaceRequest bookingSpaceRequest = new BookingSpaceRequest();
        Vehicle vehicle = new Vehicle();
        TwoWheeler twoWheeler = new TwoWheeler();
        FourWheeler fourWheeler = new FourWheeler();
        bookingSpaceRequest.setUserId(1);
        bookingSpaceRequest.setBookingSpaceName(name);
        bookingSpaceRequest.setLatitude(latitude);
        bookingSpaceRequest.setLongitude(longitude);
//        bookingSpaceRequest.setImage(imageString);
        bookingSpaceRequest.setFromTime("2019-10-19T18:35:53.741990");
        bookingSpaceRequest.setToTime("2019-10-19T18:43:04.036060");
        twoWheeler.setCharge(Float.parseFloat(c2));
        twoWheeler.setQuantity(Integer.parseInt(q2));
        fourWheeler.setCharge(Float.parseFloat(c4));
        fourWheeler.setQuantity(Integer.parseInt(q4));
        vehicle.setTwoWheeler(twoWheeler);
        vehicle.setFourWheeler(fourWheeler);
        bookingSpaceRequest.setVehicle(vehicle);

        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);


        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContext().getContentResolver().getType(selectedImage)),
                        new File(picturePath)
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", new File(picturePath).getName(), requestFile);

        // add another part within the multipart request
        String desc = gson.toJson(bookingSpaceRequest);
        RequestBody description =
                RequestBody.create(okhttp3.MultipartBody.FORM, desc);

        Call<BookingSpaceResponse> call = apiServices.requestBookingSpace(description, body);
        call.enqueue(new Callback<BookingSpaceResponse>() {
            @Override
            public void onResponse(Call<BookingSpaceResponse> call, Response<BookingSpaceResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    BookingSpaceResponse bookingSpaceResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<BookingSpaceResponse> call, Throwable t) {
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
