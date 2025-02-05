package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.exceptions.RestaurantNotFoundException;
import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.repository.MenuRepository;
import com.project.fooddeliveryapp.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public List<Menus> addMenus(List<Menus> menus) {
        return menuRepository.saveAll(menus);
    }

    public List<Menus> getMenus() {
        return menuRepository.findAll();
    }

    public CompletableFuture<Menus> getMenusByRestaurant(Long restaurantId) {
        return CompletableFuture.supplyAsync(() ->
                restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId))
        ).thenApply(Restaurants::getMenu);
    }
}
