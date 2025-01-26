package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.food.Menus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menus, Long> {
}
