package com.example.b21yassine.groceries.models;

public class Grocery {

    public int id;
    public String name;
    public String quantity;
    public String date_added;

    public Grocery() {
    }

    public Grocery(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Grocery(String name, String quantity, String date_added) {
        this.name = name;
        this.quantity = quantity;
        this.date_added = date_added;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}
