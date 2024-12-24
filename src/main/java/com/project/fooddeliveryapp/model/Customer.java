package com.project.fooddeliveryapp.model;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
