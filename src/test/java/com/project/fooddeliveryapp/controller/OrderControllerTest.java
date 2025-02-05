package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.dto.OrderItemsDto;
import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.service.OrderService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;
    private OrdersDto sampleOrdersDto;
    private List<OrdersDto> ordersDtoList;
    private List<OrderItemsDto> orderItemsDtoList;

    @BeforeEach
    public void setUp() throws Exception {
        sampleOrdersDto = new OrdersDto(1L, "2025-02-05T14:00:00", "John Doe", 100.0, 2);
        ordersDtoList = Arrays.asList(sampleOrdersDto);

        OrderItemsDto orderItemDto = new OrderItemsDto("Pizza", 9.99, 2);
        orderItemsDtoList = Arrays.asList(orderItemDto);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testAddOrders() throws Exception {
        String orderJson = Files.readString(Paths.get("src/test/resources/mocks/order.json"), StandardCharsets.UTF_8);

        when(orderService.addOrders(any(Orders.class), anyLong())).thenReturn(sampleOrdersDto);

        mockMvc.perform(post("/order/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson)
                        .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.orderTime").value("2025-02-05T14:00:00"))
                .andExpect(jsonPath("$.deliveryPartnerName").value("John Doe"))
                .andExpect(jsonPath("$.totalAmount").value(100.0))
                .andExpect(jsonPath("$.orderItemsCount").value(2));

        verify(orderService, times(1)).addOrders(any(Orders.class), anyLong());
    }

    @Test
    public void testGetOrdersByCustomerId() throws Exception {
        when(orderService.getOrdersByCustomerId(anyLong())).thenReturn(ordersDtoList);

        mockMvc.perform(get("/order/get")
                        .param("customerId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value(1))
                .andExpect(jsonPath("$[0].orderTime").value("2025-02-05T14:00:00"))
                .andExpect(jsonPath("$[0].deliveryPartnerName").value("John Doe"))
                .andExpect(jsonPath("$[0].totalAmount").value(100.0))
                .andExpect(jsonPath("$[0].orderItemsCount").value(2));

        verify(orderService, times(1)).getOrdersByCustomerId(anyLong());
    }

    @Test
    public void testGetOrderItemsByOrderId() throws Exception {
        when(orderService.getOrderItemsByOrderId(anyLong())).thenReturn(orderItemsDtoList);

        mockMvc.perform(get("/order/getOrderItems")
                        .param("orderId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[0].price").value(9.99))
                .andExpect(jsonPath("$[0].quantity").value(2));

        verify(orderService, times(1)).getOrderItemsByOrderId(anyLong());
    }
}
