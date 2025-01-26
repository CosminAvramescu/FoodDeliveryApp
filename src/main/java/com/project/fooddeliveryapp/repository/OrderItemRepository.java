package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.orders.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
