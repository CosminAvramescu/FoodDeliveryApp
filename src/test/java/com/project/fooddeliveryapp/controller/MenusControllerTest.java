package com.project.fooddeliveryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.service.MenuService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class MenusControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MenuService menuService;

    @InjectMocks
    private MenusController menusController;

    private List<Menus> menusList;
    private Menus singleMenu;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        Menus menu1 = new Menus();
        menu1.setId(1L);

        Menus menu2 = new Menus();
        menu2.setId(2L);

        menusList = Arrays.asList(menu1, menu2);
        singleMenu = menu1;

        mockMvc = MockMvcBuilders.standaloneSetup(menusController).build();
    }

    @Test
    public void testAddMenus() throws Exception {
        String jsonContent = Files.readString(Paths.get("src/test/resources/mocks/menus.json"), StandardCharsets.UTF_8);

        when(menuService.addMenus(anyList())).thenReturn(menusList);

        mockMvc.perform(post("/menu/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(menuService, times(1)).addMenus(anyList());
    }

    @Test
    public void testGetMenus() throws Exception {
        when(menuService.getMenus()).thenReturn(menusList);

        mockMvc.perform(get("/menu/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(menuService, times(1)).getMenus();
    }

    @Test
    public void testGetMenusByRestaurant() throws Exception {
        when(menuService.getMenusByRestaurant(anyLong()))
                .thenReturn(CompletableFuture.completedFuture(singleMenu));

        mockMvc.perform(get("/menu/getByRestaurant")
                        .param("restaurantId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(menuService, times(1)).getMenusByRestaurant(anyLong());
    }
}
