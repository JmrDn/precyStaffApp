package com.example.staff_precyapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staff_precyapp.Model.UpComingEventsModel;
import com.example.staff_precyapp.Model.UpComingEventsModel;
import com.example.staff_precyapp.R;

import java.util.ArrayList;

public class UpComingEventsAdapter extends RecyclerView.Adapter<UpComingEventsAdapter.MyViewHolder> {

    Context context;
    ArrayList<UpComingEventsModel>upComingEventsModel;

    public UpComingEventsAdapter (Context context, ArrayList<UpComingEventsModel> upComingEventsModel){
        this.context = context;
        this.upComingEventsModel = upComingEventsModel;



    }

    @NonNull
    @Override
    public UpComingEventsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.upcoming_event_list, parent, false);
        return new UpComingEventsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingEventsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(upComingEventsModel.get(position).getName());
        holder.when.setText(upComingEventsModel.get(position).getWhen());
        holder.where.setText(upComingEventsModel.get(position).getWhere());


    }

    @Override
    public int getItemCount() {
        return upComingEventsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, where, when;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            where = itemView.findViewById(R.id.where_TextView);
            when = itemView.findViewById(R.id.when_TextView);
        }
    }
}
