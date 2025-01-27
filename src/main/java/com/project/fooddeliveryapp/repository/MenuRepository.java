package com.project.fooddeliveryapp.repository;

import com.project.fooddeliveryapp.model.food.Menus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menus, Long> {
}
