package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenusController {
    @Autowired
    private final MenuService menuService;
    @PostMapping("/add")
    public List<Menus> addMenu(@RequestBody List<Menus> menus) {
        return menuService.addMenu(menus);
    }
}
