package com.project.fooddeliveryapp.model.users;

import com.project.fooddeliveryapp.model.orders.Orders;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Customers extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();
    private String address;

    public Long getId() {
        return id;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
