package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.ProductItems;
import com.project.fooddeliveryapp.model.orders.OrderItems;
import com.project.fooddeliveryapp.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private List<ProductItems> productItemsList;
    private OrderItems orderItem;

    @BeforeEach
    public void setUp() {
        productItemsList = new ArrayList<>();
        ProductItems productItem = new ProductItems();
        productItem.setId(1L);
        productItem.setName("Pizza");
        productItem.setPrice(9.99);
        productItemsList.add(productItem);

        orderItem = new OrderItems();
        orderItem.setId(1L);
        orderItem.setQuantity(2);

        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testAddProductItems() throws Exception {
        String jsonContent = Files.readString(Paths.get("src/test/resources/mocks/productItems.json"));

        when(itemService.addProductItems(anyList())).thenReturn(productItemsList);

        mockMvc.perform(post("/items/add/productItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[0].price").value(9.99));

        verify(itemService, times(1)).addProductItems(anyList());
    }

    @Test
    public void testAddOrderItem() throws Exception {
        String jsonContent = Files.readString(Paths.get("src/test/resources/mocks/orderItem.json"));

        when(itemService.addOrderItem(any(OrderItems.class))).thenReturn(orderItem);

        mockMvc.perform(post("/items/add/orderItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.quantity").value(2));

        verify(itemService, times(1)).addOrderItem(any(OrderItems.class));
    }

    @Test
    public void testGetProductItems() throws Exception {
        when(itemService.getProductItems()).thenReturn(java.util.concurrent.CompletableFuture.completedFuture(productItemsList));

        mockMvc.perform(get("/items/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[0].price").value(9.99));

        verify(itemService, times(1)).getProductItems();
    }
}

