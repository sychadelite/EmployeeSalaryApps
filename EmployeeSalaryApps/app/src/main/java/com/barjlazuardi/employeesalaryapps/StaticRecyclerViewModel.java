package com.barjlazuardi.employeesalaryapps;

public class StaticRecyclerViewModel {

    private int image;
    private String name;

    public StaticRecyclerViewModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
