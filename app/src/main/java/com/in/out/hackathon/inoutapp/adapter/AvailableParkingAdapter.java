package com.in.out.hackathon.inoutapp.adapter;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.activities.MainActivity;
import com.in.out.hackathon.inoutapp.fragments.DetailParkingFragment;
import com.in.out.hackathon.inoutapp.models.ParkingPlaceData;
import com.in.out.hackathon.inoutapp.utils.SharedPrefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AvailableParkingAdapter extends RecyclerView.Adapter<AvailableParkingAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ParkingPlaceData> parkingPlaceDataList = new ArrayList<>();
    private Gson gson;
    private SharedPrefs sharedPrefs;

    public AvailableParkingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        gson = new Gson();
        sharedPrefs = new SharedPrefs(context);
    }

    public void setParkingPlaceDataList(List<ParkingPlaceData> parkingPlaceDataList) {
        this.parkingPlaceDataList = parkingPlaceDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parkings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ParkingPlaceData data = parkingPlaceDataList.get(position);
//        Log.e("====", data.toString());
        int countBike = 0;
        int countCar = 0;
        Float chargeCar = 0.0f;
        Float chargeBike = 0.0f;
        holder.tvName.setText(data.getName());
        holder.ratingBar.setRating(getRandom());
//        Glide.with(context).load().into(holder.ivParkingSpace);
        for(int i=0;i<parkingPlaceDataList.size();i++){
            if(i!=position){
                if(data.getLocation().getId().equals(parkingPlaceDataList.get(i).getLocation().getId())){
                    if(data.getVehicle().getType().equals("TWO")){
                        countBike = data.getVehicle().getQuantity();
                        countCar = parkingPlaceDataList.get(i).getVehicle().getQuantity();
                        chargeCar = parkingPlaceDataList.get(i).getVehicle().getCharge();
                        chargeBike = data.getVehicle().getCharge();
                    }
                    else{
                        countBike = parkingPlaceDataList.get(i).getVehicle().getQuantity();
                        countCar = data.getVehicle().getQuantity();
                        chargeCar = data.getVehicle().getCharge();
                        chargeBike = parkingPlaceDataList.get(i).getVehicle().getCharge();
                    }
                }

            }
        }
        Log.e("====", countBike+" "+countCar+" "+chargeBike+" "+chargeBike+" ");
        data.setBikeAvailable(countBike);
        data.setCarAvailable(countCar);
        data.setBikeCharge(chargeBike);
        data.setCarCharge(chargeCar);
        holder.tvChargesBike.setText("\u20B9"+ " " + (chargeBike));
        holder.tvChargesCar.setText("\u20B9 " + (chargeCar));
        holder.tvBikeAvailable.setText(Integer.toString(countBike));
        holder.tvCarAvailable.setText(Integer.toString(countCar));

        try {
            holder.tvLocation.setText(getAddress(data.getLocation().getLat(), data.getLocation().getLon()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "+91-8963833132"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
                    context.startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });
        holder.cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefs.setSelectedParking(gson.toJson(data));
                DetailParkingFragment detailParkingFragment = DetailParkingFragment.newInstance();
                ((MainActivity)context).createFragment(detailParkingFragment, "Detail Parking fragment", false);
            }
        });


    }

    @Override
    public int getItemCount() {
//        Log.e("======", parkingPlaceDataList.size()+"");
        return parkingPlaceDataList.size();
    }

    private String getAddress(Float lat, Float lon) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        addresses = geocoder.getFromLocation(lat, lon, 1);
        return addresses.get(0).getAddressLine(0);
    }

    private Integer getRandom(){
        Random random = new Random();
        int rand = 0;
        while (true){
            rand = random.nextInt(11);
            if(rand !=0) break;
        }
        return rand;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvLocation, tvChargesBike, tvChargesCar, tvBikeAvailable, tvCarAvailable;
        public CardView cvParent;
        public ImageView ivParkingSpace;
        public Button btnCall;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_parking_name);
            tvChargesBike = itemView.findViewById(R.id.tv_charges_bike);
            tvChargesCar = itemView.findViewById(R.id.tv_charges_car);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvBikeAvailable = itemView.findViewById(R.id.tv_bike_availability);
            tvCarAvailable = itemView.findViewById(R.id.tv_car_availability);
            cvParent = itemView.findViewById(R.id.cv_parent);
            ivParkingSpace = itemView.findViewById(R.id.iv_parking);
            btnCall = itemView.findViewById(R.id.btn_call);
            ratingBar = itemView.findViewById(R.id.ratingParking);
        }
    }
}

