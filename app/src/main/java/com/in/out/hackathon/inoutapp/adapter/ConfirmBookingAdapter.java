package com.in.out.hackathon.inoutapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.models.ConfirmationRequest;
import com.in.out.hackathon.inoutapp.models.ConfirmationResponse;
import com.in.out.hackathon.inoutapp.models.ParkingPlaceData;
import com.in.out.hackathon.inoutapp.restapi.ApiServices;
import com.in.out.hackathon.inoutapp.restapi.AppClient;
import com.in.out.hackathon.inoutapp.utils.NetworkUtils;
import com.in.out.hackathon.inoutapp.utils.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class ConfirmBookingAdapter extends RecyclerView.Adapter<ConfirmBookingAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ParkingPlaceData> parkingPlaceDataList = new ArrayList<>();
    private ProgressDialog progressDialog;

    public ConfirmBookingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        progressDialog = new ProgressDialog();
    }


    public void setParkingPlaceDataList(List<ParkingPlaceData> parkingPlaceDataList) {
        this.parkingPlaceDataList = parkingPlaceDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirm_booking_item, parent, false);
        return new ConfirmBookingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final ParkingPlaceData data = parkingPlaceDataList.get(position);
        int countBike = 0;
        int countCar = 0;
        Float chargeCar = 0.0f;
        Float chargeBike = 0.0f;
        holder.tvParkingNameC.setText(data.getName());

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
        data.setBikeAvailable(countBike);
        data.setCarAvailable(countCar);
        data.setBikeCharge(chargeBike);
        data.setCarCharge(chargeCar);
        holder.tvChargesBikeC.setText("\u20B9"+ " " + Float.toString(chargeBike));
        holder.tvChargesCarC.setText("\u20B9 " + Float.toString(chargeCar));
        holder.tvBikeAvC.setText(Integer.toString(countBike));
        holder.tvCarAvC.setText(Integer.toString(countCar));


        Glide.with(context).load(data.getImage()).into(holder.ivParkingPic);
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallAccept(data.getId());
            }
        });
        holder.btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCallDeny(data.getId());
            }
        });

    }

    private void apiCallAccept(int id) {
        progressDialog.showDialog("Submitting your choice...", context);
        ConfirmationRequest confirmationRequest = new ConfirmationRequest();
        confirmationRequest.setId(id);
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<ConfirmationResponse> call = apiServices.requestAccept(confirmationRequest);
        call.enqueue(new Callback<ConfirmationResponse>() {
            @Override
            public void onResponse(Call<ConfirmationResponse> call, Response<ConfirmationResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    Toast.makeText(context, "You have accepted the application successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ConfirmationResponse> call, Throwable t) {
                progressDialog.hideDialog();
                if (context != null) {
                    if (!NetworkUtils.isNetworkAvailable(context)) {
                        Toast.makeText(context, "No internet connection. Please try again.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e("error--", "server error");
                        Toast.makeText(context, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void apiCallDeny(int id) {
        progressDialog.showDialog("Submitting your choice...", context);
        ConfirmationRequest confirmationRequest = new ConfirmationRequest();
        confirmationRequest.setId(id);
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<ConfirmationResponse> call = apiServices.requestDeny(confirmationRequest);
        call.enqueue(new Callback<ConfirmationResponse>() {
            @Override
            public void onResponse(Call<ConfirmationResponse> call, Response<ConfirmationResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    Toast.makeText(context, "You have rejected the application successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ConfirmationResponse> call, Throwable t) {
                progressDialog.hideDialog();
                if (context != null) {
                    if (!NetworkUtils.isNetworkAvailable(context)) {
                        Toast.makeText(context, "No internet connection. Please try again.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e("error--", "server error");
                        Toast.makeText(context, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return parkingPlaceDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvParkingNameC, tvChargesBikeC, tvChargesCarC, tvBikeAvC, tvCarAvC;
        ImageView ivParkingPic;
        Button btnAccept, btnDeny;

        public ViewHolder(View itemView) {
            super(itemView);
            btnAccept = itemView.findViewById(R.id.btn_accept);
            btnDeny = itemView.findViewById(R.id.btn_deny);
            tvParkingNameC = itemView.findViewById(R.id.tv_parking_confirm_name);
            tvChargesBikeC = itemView.findViewById(R.id.tv_charges_bike_confirm);
            tvChargesCarC = itemView.findViewById(R.id.tv_charges_car_confirm);
            tvBikeAvC = itemView.findViewById(R.id.tv_bike_availability_confirm);
            tvCarAvC = itemView.findViewById(R.id.tv_car_availability_confirm);
            ivParkingPic = itemView.findViewById(R.id.iv_parking_pic);
        }
    }


}
