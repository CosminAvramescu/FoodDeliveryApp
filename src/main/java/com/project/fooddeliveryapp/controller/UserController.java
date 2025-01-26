package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.dto.CustomersDto;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/add/customer")
    public List<Customers> addCustomer(@RequestBody List<Customers> customers) {
        return userService.addCustomer(customers);
    }

    @PostMapping("/add/deliveryPartner")
    public List<DeliveryPartners> addDeliveryPartner(@RequestBody List<DeliveryPartners> deliveryPartner) {
        return userService.addDeliveryPartner(deliveryPartner);
    }
}
