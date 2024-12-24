package com.project.fooddeliveryapp.model;

import jakarta.persistence.Entity;

@Entity
public class DeliveryPartner extends User {
    private String vehicleDetails;
    private Boolean available;

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

