package com.project.fooddeliveryapp.model.orders;

import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;
    @ManyToOne
    @JoinColumn(name = "deliveryPartner_id", nullable = false)
    private DeliveryPartners deliveryPartner;
    private Double totalAmount;

    public Long getId() {
        return id;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public DeliveryPartners getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(DeliveryPartners deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }
}

