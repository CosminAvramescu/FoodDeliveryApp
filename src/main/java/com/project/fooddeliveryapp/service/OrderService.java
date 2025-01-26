package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.exceptions.UserNotFoundException;
import com.project.fooddeliveryapp.model.orders.OrderItems;
import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    private final OrderItemRepository orderItemRepository;

    @Autowired
    private final ProductItemRepository productItemRepository;

    @Value("#{T(java.time.LocalDateTime).now()}")
    private String orderTime;

    public OrdersDto addOrder(Orders order, Long customerId) {
        try {
            Customers customer = customerRepository.findById(customerId).orElseThrow();
            order.setCustomer(customer);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(customerId);
        }

        for (OrderItems orderItem : order.getOrderItems()) {
            orderItem.setProductItem(productItemRepository.findById(orderItem.getProductItem().getId()).orElseThrow());
        }
        orderItemRepository.saveAll(order.getOrderItems());

        for (DeliveryPartners deliveryPartner : deliveryPartnerRepository.findAll()) {
            if (deliveryPartner.getAvailable()) {
                order.setDeliveryPartner(deliveryPartner);
                deliveryPartner.setAvailable(false);
                break;
            }
        }

        order.setOrderTime(orderTime);

        order.setTotalAmount(
                order.getOrderItems().stream()
                        .mapToDouble(orderItem -> orderItem.getProductItem().getPrice() * orderItem.getQuantity())
                        .sum()
        );

        int totalQuantity = order.getOrderItems().stream()
                .mapToInt(OrderItems::getQuantity)
                .sum();

        orderRepository.save(order);

        return new OrdersDto(order.getCustomer().getId(), order.getOrderTime(), order.getDeliveryPartner().getName(), order.getTotalAmount(), totalQuantity);
    }
}
