package com.barjlazuardi.employeesalaryapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        //Required Empty Public Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        CardView pegawai = (CardView) v.findViewById(R.id.card_pegawai);
        pegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_peg = new Intent(getActivity(), PegawaiActivity.class);
                startActivity(intent_peg);
            }
        });

        CardView division = (CardView) v.findViewById(R.id.card_division);
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_div = new Intent(getActivity(), GolonganActivity.class);
                startActivity(intent_div);
            }
        });

        return v;

    }

}
