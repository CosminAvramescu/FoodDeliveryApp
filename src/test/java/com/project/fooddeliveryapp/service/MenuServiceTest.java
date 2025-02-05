package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.exceptions.RestaurantNotFoundException;
import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.repository.MenuRepository;
import com.project.fooddeliveryapp.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuService menuService;

    private Restaurants restaurant1;
    private List<Menus> menusList;

    @BeforeEach
    public void setUp() {
        Menus menu1 = new Menus();
        menu1.setId(1L);

        Menus menu2 = new Menus();
        menu2.setId(2L);

        restaurant1 = new Restaurants();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurant 1");
        restaurant1.setMenu(menu1);

        menusList = Arrays.asList(menu1, menu2);
    }

    @Test
    public void testAddMenus() {
        when(menuRepository.saveAll(anyList())).thenReturn(menusList);

        List<Menus> result = menuService.addMenus(menusList);

        verify(menuRepository, times(1)).saveAll(anyList());
        assert result != null;
        assert result.size() == 2;
        assert result.get(0).getId() == 1L;
        assert result.get(1).getId() == 2L;
    }

    @Test
    public void testGetMenus() {
        when(menuRepository.findAll()).thenReturn(menusList);

        List<Menus> result = menuService.getMenus();

        verify(menuRepository, times(1)).findAll();
        assert result != null;
        assert result.size() == 2;
        assert result.get(0).getId() == 1L;
        assert result.get(1).getId() == 2L;
    }

    @Test
    public void testGetMenusByRestaurant() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant1));

        CompletableFuture<Menus> result = menuService.getMenusByRestaurant(1L);

        result.join();

        verify(restaurantRepository, times(1)).findById(anyLong());

        result.thenAccept(menus -> {
            assert menus != null;
            assert menus.getId() == 1L;
        });
    }

    @Test
    public void testGetMenusByRestaurantNotFound() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            menuService.getMenusByRestaurant(1L).join();
        } catch (Exception e) {
            assert e.getCause() instanceof RestaurantNotFoundException;
            assert e.getCause().getMessage().contains("Restaurant with id " + restaurant1.getId() + " not found.");
        }
    }
}
