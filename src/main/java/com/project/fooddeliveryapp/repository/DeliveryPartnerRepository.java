package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartners, Long> {
}
