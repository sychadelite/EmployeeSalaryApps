package com.barjlazuardi.employeesalaryapps;

public class MostViewedHelperClass {

    int image;
    float rating;
    String name;

    public MostViewedHelperClass(int image, float rating, String name) {
        this.image = image;
        this.rating = rating;
        this.name = name;
    }

    public int getImageView() {
        return image;
    }

    public float getRating() { return rating; }

    public String getTextView() {
        return name;
    }
}
