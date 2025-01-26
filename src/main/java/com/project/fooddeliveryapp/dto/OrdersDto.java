package com.project.fooddeliveryapp.dto;

public record OrdersDto(Long customerId, String orderTime, String deliveryPartnerName, Double totalAmount, int orderItemsCount) {
}
