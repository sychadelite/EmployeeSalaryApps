package com.barjlazuardi.employeesalaryapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StakeholdersAdapter extends RecyclerView.Adapter<StakeholdersAdapter.StakeholdersViewHolder> {

    ArrayList<StakeholdersHelperClass> stakeholdersLocations;

    public StakeholdersAdapter(ArrayList<StakeholdersHelperClass> stakeholdersLocations) {
        this.stakeholdersLocations = stakeholdersLocations;
    }

    @NonNull
    @Override
    public StakeholdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stakeholders_card_design,parent,false);
        StakeholdersViewHolder stakeholdersViewHolder = new StakeholdersViewHolder(view);

        return stakeholdersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StakeholdersViewHolder holder, int position) {

        StakeholdersHelperClass stakeholdersHelperClass = stakeholdersLocations.get(position);

        holder.image.setImageResource(stakeholdersHelperClass.getImage());
        holder.rating.setRating(stakeholdersHelperClass.getRating());
        holder.name.setText(stakeholdersHelperClass.getName());
        holder.desc.setText(stakeholdersHelperClass.getDesc());

    }

    @Override
    public int getItemCount() {
        return stakeholdersLocations.size();
    }

    public static class StakeholdersViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        RatingBar rating;
        TextView name, desc;

        public StakeholdersViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.stakeholders_img);
            rating = itemView.findViewById(R.id.stakeholders_rating);
            name = itemView.findViewById(R.id.stakeholders_name);
            desc = itemView.findViewById(R.id.stakeholders_desc);
        }
    }

}
