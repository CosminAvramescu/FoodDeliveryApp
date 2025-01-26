package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
