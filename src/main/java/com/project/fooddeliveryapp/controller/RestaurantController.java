package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    @Autowired
    private final RestaurantService restaurantService;

    @PostMapping("/add")
    public List<Restaurants> addRestaurant(@RequestBody List<Restaurants> restaurants) {
        return restaurantService.addRestaurant(restaurants);
    }

    @GetMapping("/get")
    public List<Restaurants> getRestaurants() {
        return restaurantService.getRestaurants();
    }
}
