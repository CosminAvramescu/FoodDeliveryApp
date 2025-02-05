package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    @Autowired
    private final RestaurantService restaurantService;

    @PostMapping("/add")
    public List<Restaurants> addRestaurants(@RequestBody List<Restaurants> restaurants) {
        return restaurantService.addRestaurants(restaurants);
    }

    @GetMapping("/get")
    public List<Restaurants> getRestaurants() throws ExecutionException, InterruptedException {
        return restaurantService.getRestaurants().get();
    }
}
