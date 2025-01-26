package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.food.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurants, Long> {
}
