package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.repository.MenuRepository;
import com.project.fooddeliveryapp.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final MenuRepository menuRepository;

    public List<Restaurants> addRestaurant(List<Restaurants> restaurants) {
        for (Restaurants restaurant : restaurants) {
            restaurant.setMenu(menuRepository.findById(restaurant.getMenu().getId()).orElseThrow());
        }
        return restaurantRepository.saveAll(restaurants);
    }
}
