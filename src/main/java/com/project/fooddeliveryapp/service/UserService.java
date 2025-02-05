package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.dto.CustomersDto;
import com.project.fooddeliveryapp.dto.DeliveryPartnersDto;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.repository.CustomerRepository;
import com.project.fooddeliveryapp.repository.DeliveryPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    public List<Customers> addCustomers(List<Customers> customers) {
        return customerRepository.saveAll(customers);
    }

    public List<DeliveryPartners> addDeliveryPartner(List<DeliveryPartners> deliveryPartners) {
        return deliveryPartnerRepository.saveAll(deliveryPartners);
    }

    public List<CustomersDto> getCustomers() {
        List<CustomersDto> customersDto = new ArrayList<>();

        for (Customers customer : customerRepository.findAll()) {
            customersDto.add(new CustomersDto(customer.getId(), customer.getName(),
                    customer.getPhone(), customer.getAddress()));
        }

        return customersDto;
    }

    public List<DeliveryPartnersDto> getDeliveryPartners() {
        List<DeliveryPartnersDto> deliveryPartnersDto = new ArrayList<>();

        for (DeliveryPartners deliveryPartner : deliveryPartnerRepository.findAll()) {
            deliveryPartnersDto.add(new DeliveryPartnersDto(deliveryPartner.getId(), deliveryPartner.getName(),
                    deliveryPartner.getPhone(), deliveryPartner.getVehicleDetails()));
        }

        return deliveryPartnersDto;
    }
}
