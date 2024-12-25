package com.project.fooddeliveryapp.model.users;

import com.project.fooddeliveryapp.model.orders.Orders;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class DeliveryPartners extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "deliveryPartner")
    private List<Orders> orders = new ArrayList<>();
    private String vehicleDetails;
    private Boolean available;

    public Long getId() {
        return id;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

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
