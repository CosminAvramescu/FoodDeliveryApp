package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.food.ProductItems;
import com.project.fooddeliveryapp.model.orders.OrderItems;
import com.project.fooddeliveryapp.repository.MenuRepository;
import com.project.fooddeliveryapp.repository.OrderItemRepository;
import com.project.fooddeliveryapp.repository.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    @Autowired
    private final OrderItemRepository orderItemRepository;

    @Autowired
    private final ProductItemRepository productItemRepository;

    @Autowired
    private final MenuRepository menuRepository;

    public List<ProductItems> addProductItem(List<ProductItems> productItems) {
        for (ProductItems productItem : productItems) {
            productItem.setMenu(menuRepository.findById(productItem.getMenu().getId()).orElseThrow());
        }
        return productItemRepository.saveAll(productItems);
    }

    public OrderItems addOrderItem(OrderItems orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
