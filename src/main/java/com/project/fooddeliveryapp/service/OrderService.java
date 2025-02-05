package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.dto.OrderItemsDto;
import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.exceptions.AvailableDeliveryPartnerNotFoundException;
import com.project.fooddeliveryapp.exceptions.OrderNotFoundException;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    @Value("${discount.limit}")
    private double discountLimit;

    @Value("${discount.rate}")
    private double discountRate;

    public OrdersDto addOrders(Orders order, Long customerId) {
        Customers customer;
        try {
            customer = customerRepository.findById(customerId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(customerId);
        }

        boolean found = false;
        for (DeliveryPartners deliveryPartner : deliveryPartnerRepository.findAll()) {
            if (deliveryPartner.getAvailable()) {
                order.setDeliveryPartner(deliveryPartner);
                deliveryPartner.setAvailable(false);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new AvailableDeliveryPartnerNotFoundException();
        }

        for (OrderItems orderItem : order.getOrderItems()) {
            orderItem.setProductItem(productItemRepository.findById(orderItem.getProductItem().getId()).orElseThrow());
        }
        orderItemRepository.saveAll(order.getOrderItems());

        order.setCustomer(customer);
        order.setOrderTime(new Date().toString());

        order.setTotalAmount(
                BigDecimal.valueOf(
                        order.getOrderItems().stream()
                                .mapToDouble(orderItem -> orderItem.getProductItem().getPrice() * orderItem.getQuantity())
                                .sum()
                ).setScale(2, RoundingMode.HALF_UP).doubleValue()
        );

        if (order.getTotalAmount() >= discountLimit) {
            order.setTotalAmount(order.getTotalAmount() * (1 - discountRate));
        }

        int totalQuantity = order.getOrderItems().stream()
                .mapToInt(OrderItems::getQuantity)
                .sum();

        orderRepository.save(order);

        if (customer.getOrders() == null) {
            customer.setOrders(new ArrayList<>());
        } else {
            customer.getOrders().add(order);
        }
        customerRepository.save(customer);

        return new OrdersDto(order.getCustomer().getId(), order.getOrderTime(),
                order.getDeliveryPartner().getName(), order.getTotalAmount(), totalQuantity);
    }

    public List<OrdersDto> getOrdersByCustomerId(Long customerId) {
        Customers customer;
        try {
            customer = customerRepository.findById(customerId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(customerId);
        }

        List<OrdersDto> ordersDto = new ArrayList<>();
        for (Orders order : customer.getOrders()) {
            int totalQuantity = order.getOrderItems().stream()
                    .mapToInt(OrderItems::getQuantity)
                    .sum();

            ordersDto.add(new OrdersDto(order.getCustomer().getId(), order.getOrderTime(),
                    order.getDeliveryPartner().getName(), order.getTotalAmount(), totalQuantity));
        }
        return ordersDto;
    }

    public List<OrderItemsDto> getOrderItemsByOrderId(Long orderId) {
        Orders order;
        try {
            order = orderRepository.findById(orderId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException(orderId);
        }

        List<OrderItemsDto> orderItemsDto = new ArrayList<>();
        for (OrderItems orderItem : order.getOrderItems()) {
            orderItemsDto.add(new OrderItemsDto(orderItem.getProductItem().getName(),
                    orderItem.getProductItem().getPrice(), orderItem.getQuantity()));
        }

        return orderItemsDto;
    }
}
