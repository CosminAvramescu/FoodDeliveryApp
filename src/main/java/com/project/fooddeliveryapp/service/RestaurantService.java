package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.repository.MenuRepository;
import com.project.fooddeliveryapp.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final MenuRepository menuRepository;

    public List<Restaurants> addRestaurants(List<Restaurants> restaurants) {
        for (Restaurants restaurant : restaurants) {
            restaurant.setMenu(menuRepository.findById(restaurant.getMenu().getId()).orElseThrow());
        }
        return restaurantRepository.saveAll(restaurants);
    }

    public CompletableFuture<List<Restaurants>> getRestaurants() {
        return CompletableFuture.supplyAsync(restaurantRepository::findAll);
    }
}
