package com.in.out.hackathon.inoutapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.in.out.hackathon.inoutapp.R;

import java.util.List;

public class AvailableParkingAdapter extends RecyclerView.Adapter<AvailableParkingAdapter.ViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;


    public AvailableParkingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parkings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvLocation, tvCharges;
        public CardView cvParent;
        public ImageView ivParkingSpace;
        public Button btnCall;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_parking_name);
            tvCharges = itemView.findViewById(R.id.tv_charges);
            tvLocation = itemView.findViewById(R.id.tv_location);
            cvParent = itemView.findViewById(R.id.cv_parent);
            ivParkingSpace = itemView.findViewById(R.id.iv_parking);
            btnCall = itemView.findViewById(R.id.btn_call);
        }
    }
}

