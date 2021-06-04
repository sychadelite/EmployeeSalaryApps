package com.barjlazuardi.employeesalaryapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OnBoardingFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3, container, false);

        TextView skip = (TextView) v.findViewById(R.id.txt_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_skip = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_skip);
            }
        });

        FloatingActionButton btnGo = (FloatingActionButton) v.findViewById(R.id.floating_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_go = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_go);
            }
        });

        return v;
    }
}
