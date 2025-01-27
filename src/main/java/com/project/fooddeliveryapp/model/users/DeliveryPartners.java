package com.project.fooddeliveryapp.model.users;

import com.project.fooddeliveryapp.model.orders.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class DeliveryPartners extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "deliveryPartner")
    private List<Orders> orders = new ArrayList<>();

    private String vehicleDetails;

    private Boolean available;
}
