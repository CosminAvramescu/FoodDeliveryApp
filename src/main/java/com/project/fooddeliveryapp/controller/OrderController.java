package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.dto.OrderItemsDto;
import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.service.OrderService;
import lombok.Getter;
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
    public OrdersDto addOrder(@RequestBody Orders order, @RequestParam Long customerId) {
        return orderService.addOrder(order, customerId);
    }

    @GetMapping("/get")
    public List<OrdersDto> getOrderByCustomerId(@RequestParam Long customerId) {
        return orderService.getOrderByCustomerId(customerId);
    }

    @GetMapping("/getOrderItems")
    public List<OrderItemsDto> getOrderItems(@RequestParam Long orderId) {
        return orderService.getOrderItems(orderId);
    }
}
