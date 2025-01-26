package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.dto.CustomersDto;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.model.users.Users;
import com.project.fooddeliveryapp.repository.CustomerRepository;
import com.project.fooddeliveryapp.repository.DeliveryPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public List<Customers> addCustomer(List<Customers> customers) {
        return customerRepository.saveAll(customers);
    }

    public List<DeliveryPartners> addDeliveryPartner(List<DeliveryPartners> deliveryPartners) {
        return deliveryPartnerRepository.saveAll(deliveryPartners);
    }
}
