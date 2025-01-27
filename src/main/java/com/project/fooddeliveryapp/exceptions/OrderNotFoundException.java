package com.project.fooddeliveryapp.exceptions;

public class OrderNotFoundException extends RuntimeException {
    private final Long id;

    public OrderNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Order with id " + id + " not found.";
    }
}
