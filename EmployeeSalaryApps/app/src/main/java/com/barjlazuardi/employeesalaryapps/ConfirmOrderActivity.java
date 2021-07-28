package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ConfirmOrderActivity extends AppCompatActivity {

    RecyclerView shopRv;
    ConfirmOrderActivityAdapter confirmOrderActivityAdapter;
    Activity context;
    UpdateSelectedItems updateSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        shopRv = findViewById(R.id.recycler_shop);
        confirmOrderActivityAdapter = new ConfirmOrderActivityAdapter(context);
        shopRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        shopRv.setAdapter(confirmOrderActivityAdapter);

    }
}