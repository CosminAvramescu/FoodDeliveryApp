package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenusController {
    @Autowired
    private final MenuService menuService;
    @PostMapping("/add")
    public List<Menus> addMenus(@RequestBody List<Menus> menus) {
        return menuService.addMenus(menus);
    }

    @GetMapping("/get")
    public List<Menus> getMenus() {
        return menuService.getMenus();
    }

    @GetMapping("/getByRestaurant")
    public Menus getMenus(@RequestParam Long restaurantId) throws ExecutionException, InterruptedException {
        return menuService.getMenusByRestaurant(restaurantId).get();
    }
}
