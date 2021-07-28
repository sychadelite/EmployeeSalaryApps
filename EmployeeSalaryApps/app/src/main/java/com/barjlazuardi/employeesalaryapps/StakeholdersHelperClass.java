package com.barjlazuardi.employeesalaryapps;

import android.media.Rating;
import android.widget.RatingBar;

import androidx.cardview.widget.CardView;

public class StakeholdersHelperClass {

    int image;
    float rating;
    String name, desc;


    public StakeholdersHelperClass(int image, float rating, String name, String desc) {
        this.image = image;
        this.rating = rating;
        this.name = name;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public float getRating() { return rating; }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
