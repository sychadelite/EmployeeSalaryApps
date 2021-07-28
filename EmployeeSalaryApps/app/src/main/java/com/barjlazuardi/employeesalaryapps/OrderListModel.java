package com.barjlazuardi.employeesalaryapps;

public class OrderListModel {

    private String name;
    private int price;

    public OrderListModel(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
