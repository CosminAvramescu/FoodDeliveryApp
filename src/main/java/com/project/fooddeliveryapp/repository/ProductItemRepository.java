package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.food.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItems, Long> {
}
