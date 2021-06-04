package com.barjlazuardi.employeesalaryapps;

import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {

    int image;
    String name;
    GradientDrawable gradient;

    public CategoriesHelperClass(int image, String name, GradientDrawable gradient) {
        this.image = image;
        this.name = name;
        this.gradient = gradient;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return name;
    }

    public GradientDrawable getGradient() { return gradient; }
}
