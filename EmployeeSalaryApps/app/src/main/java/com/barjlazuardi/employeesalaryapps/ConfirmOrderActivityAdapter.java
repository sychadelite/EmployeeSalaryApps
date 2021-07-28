package com.barjlazuardi.employeesalaryapps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ConfirmOrderActivityAdapter extends RecyclerView.Adapter<ConfirmOrderActivityAdapter.ConfirmOrderViewHolder> {

    private ArrayList<OrderListModel> orderListModels;
    Activity activity;

    public ConfirmOrderActivityAdapter(Activity activity) {
        this.activity = activity;
        orderListModels = ((UpdateSelectedItems) ApplicationOrder.getMyContext()).getItems();
    }

    public class ConfirmOrderViewHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        public ConfirmOrderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @NotNull
    @Override
    public ConfirmOrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent, false);
        ConfirmOrderViewHolder confirmOrderViewHolder = new ConfirmOrderViewHolder(view);
        return confirmOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ConfirmOrderActivityAdapter.ConfirmOrderViewHolder holder, int position) {
        OrderListModel currentItem = orderListModels.get(position);
        holder.name.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }
}
