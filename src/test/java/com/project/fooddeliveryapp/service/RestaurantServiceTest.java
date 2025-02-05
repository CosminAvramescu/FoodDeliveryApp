package com.project.fooddeliveryapp.service;

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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private List<Restaurants> restaurantsList;
    private Restaurants restaurant1;

    @BeforeEach
    public void setUp() {
        restaurant1 = new Restaurants();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurant 1");
        Menus menu1 = new Menus();
        menu1.setId(1L);
        restaurant1.setMenu(menu1);

        Restaurants restaurant2 = new Restaurants();
        Menus menu2 = new Menus();
        menu2.setId(2L);
        restaurant2.setId(2L);
        restaurant2.setName("Restaurant 2");
        restaurant2.setMenu(menu2);

        restaurantsList = Arrays.asList(restaurant1, restaurant2);
    }

    @Test
    public void testAddRestaurants() {
        when(menuRepository.findById(anyLong())).thenReturn(Optional.of(restaurant1.getMenu()));
        when(restaurantRepository.saveAll(anyList())).thenReturn(restaurantsList);

        List<Restaurants> result = restaurantService.addRestaurants(restaurantsList);

        verify(menuRepository, times(2)).findById(anyLong());
        verify(restaurantRepository, times(1)).saveAll(anyList());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    public void testAddRestaurantsMenuNotFound() {
        when(menuRepository.findById(anyLong())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            restaurantService.addRestaurants(restaurantsList);
        });

        verify(menuRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetRestaurants() {
        when(restaurantRepository.findAll()).thenReturn(restaurantsList);

        CompletableFuture<List<Restaurants>> result = restaurantService.getRestaurants();

        verify(restaurantRepository, times(1)).findAll();

        result.thenAccept(restaurants -> {
            assertNotNull(restaurants);
            assertEquals(2, restaurants.size());
            assertEquals("Restaurant 1", restaurants.get(0).getName());
            assertEquals("Restaurant 2", restaurants.get(1).getName());
        });
    }
}
