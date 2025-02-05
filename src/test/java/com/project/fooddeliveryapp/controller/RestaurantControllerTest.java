package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.Restaurants;
import com.project.fooddeliveryapp.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    private List<Restaurants> restaurantsList;

    @BeforeEach
    public void setUp() throws Exception {
        // Prepare test data
        Restaurants restaurant1 = new Restaurants();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurant 1");

        Restaurants restaurant2 = new Restaurants();
        restaurant2.setId(2L);
        restaurant2.setName("Restaurant 2");

        restaurantsList = Arrays.asList(restaurant1, restaurant2);

        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    public void testAddRestaurants() throws Exception {
        String jsonContent = Files.readString(Paths.get("src/test/resources/mocks/restaurants.json"), StandardCharsets.UTF_8);

        when(restaurantService.addRestaurants(anyList())).thenReturn(restaurantsList);

        mockMvc.perform(post("/restaurant/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(restaurantService, times(1)).addRestaurants(anyList());
    }

    @Test
    public void testGetRestaurants() throws Exception {
        when(restaurantService.getRestaurants()).thenReturn(CompletableFuture.completedFuture(restaurantsList));

        mockMvc.perform(get("/restaurant/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(restaurantService, times(1)).getRestaurants();
    }
}
