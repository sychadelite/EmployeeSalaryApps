package com.barjlazuardi.employeesalaryapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReelsAdapter extends RecyclerView.Adapter<ReelsAdapter.ReelsViewHolder> {

    ArrayList<ReelsHelperClass> reelsLocations;

    public ReelsAdapter(ArrayList<ReelsHelperClass> reelsLocations) {
        this.reelsLocations = reelsLocations;
    }

    @NonNull
    @Override
    public ReelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reels_design,parent,false);
        ReelsViewHolder reelsViewHolder = new ReelsViewHolder(view);

        return reelsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReelsViewHolder holder, int position) {

        ReelsHelperClass reelsHelperClass = reelsLocations.get(position);

        holder.image.setImageResource(reelsHelperClass.getImage());
        holder.name.setText(reelsHelperClass.getName());

    }

    @Override
    public int getItemCount() {
        return reelsLocations.size();
    }

    public static class ReelsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;

        public ReelsViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.profile_pic);
            name = itemView.findViewById(R.id.username);
        }
    }

}
