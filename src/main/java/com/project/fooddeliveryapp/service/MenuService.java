package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    public List<Menus> addMenu(List<Menus> menus) {
        return menuRepository.saveAll(menus);
    }
}
