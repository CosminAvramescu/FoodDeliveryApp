package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.dto.OrderItemsDto;
import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("/add")
    public synchronized OrdersDto addOrders(@RequestBody Orders order, @RequestParam Long customerId) {
        return orderService.addOrders(order, customerId);
    }

    @GetMapping("/get")
    public List<OrdersDto> getOrdersByCustomerId(@RequestParam Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/getOrderItems")
    public List<OrderItemsDto> getOrderItemsByOrderId(@RequestParam Long orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }
}
