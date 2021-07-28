package com.barjlazuardi.employeesalaryapps;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StaticRecyclerViewAdapter extends RecyclerView.Adapter<StaticRecyclerViewAdapter.StaticRecyclerViewViewHolder> {


    private ArrayList<StaticRecyclerViewModel> items;
    int row_index = -1;

    public StaticRecyclerViewAdapter(ArrayList<StaticRecyclerViewModel> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public StaticRecyclerViewViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false);
        StaticRecyclerViewViewHolder staticRecyclerViewViewHolder = new StaticRecyclerViewViewHolder(view);

        return staticRecyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StaticRecyclerViewViewHolder holder, int position) {

        StaticRecyclerViewModel currentItem = items.get(position);

        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if(row_index == position) {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            holder.textView.setTextColor(Color.WHITE);
        }else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            holder.textView.setTextColor(Color.parseColor("#191C32"));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRecyclerViewViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRecyclerViewViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_food);
            textView = itemView.findViewById(R.id.name_food);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
