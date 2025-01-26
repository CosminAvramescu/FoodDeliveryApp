package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.users.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
}
