package com.project.fooddeliveryapp.controller;

import com.project.fooddeliveryapp.model.food.ProductItems;
import com.project.fooddeliveryapp.model.orders.OrderItems;
import com.project.fooddeliveryapp.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;
    @PostMapping("/add/productItem")
    public List<ProductItems> addProductItems(@RequestBody List<ProductItems> productItems) {
        return itemService.addProductItems(productItems);
    }

    @PostMapping("/add/orderItem")
    public OrderItems addProductItem(@RequestBody OrderItems orderItem) {
        return itemService.addOrderItem(orderItem);
    }

    @GetMapping("/get")
    public List<ProductItems> getProductItems() throws ExecutionException, InterruptedException {
        return itemService.getProductItems().get();
    }
}
