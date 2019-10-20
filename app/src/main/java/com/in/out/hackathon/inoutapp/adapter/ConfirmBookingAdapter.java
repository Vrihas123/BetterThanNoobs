package com.in.out.hackathon.inoutapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.models.ConfirmBookingData;

import java.util.ArrayList;
import java.util.List;

public class ConfirmBookingAdapter extends RecyclerView.Adapter<ConfirmBookingAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ConfirmBookingData> confirmBookingDataList = new ArrayList<>();

    public ConfirmBookingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setConfirmBookingDataList(List<ConfirmBookingData> confirmBookingDataList) {
        this.confirmBookingDataList = confirmBookingDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConfirmBookingData data = confirmBookingDataList.get(position);

    }

    @Override
    public int getItemCount() {
        return confirmBookingDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);

        }
    }


}
