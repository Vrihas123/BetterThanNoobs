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
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.activities.MainActivity;
import com.in.out.hackathon.inoutapp.fragments.DetailParkingFragment;
import com.in.out.hackathon.inoutapp.models.ParkingPlaceData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AvailableParkingAdapter extends RecyclerView.Adapter<AvailableParkingAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ParkingPlaceData> parkingPlaceDataList = new ArrayList<>();

    public AvailableParkingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
        holder.tvName.setText(data.getName());
//        holder.tvCharges.setText(data.getCharges());
//        holder.ratingBar.setRating();
//        Glide.with(context).load().into(holder.ivParkingSpace);
//        holder.tvLocation.setText(getAddress());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse("tel:" + data.getPhoneNumber()));
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
                DetailParkingFragment detailParkingFragment = DetailParkingFragment.newInstance();
                ((MainActivity)context).createFragment(detailParkingFragment, "Detail Parking fragment", false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parkingPlaceDataList.size();
    }

    private String getAddress(Double lat, Double lon) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        addresses = geocoder.getFromLocation(lat, lon, 1);
        return addresses.get(0).getAddressLine(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvLocation, tvCharges;
        public CardView cvParent;
        public ImageView ivParkingSpace;
        public Button btnCall;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_parking_name);
            tvCharges = itemView.findViewById(R.id.tv_charges);
            tvLocation = itemView.findViewById(R.id.tv_location);
            cvParent = itemView.findViewById(R.id.cv_parent);
            ivParkingSpace = itemView.findViewById(R.id.iv_parking);
            btnCall = itemView.findViewById(R.id.btn_call);
            ratingBar = itemView.findViewById(R.id.ratingParking);
        }
    }
}

