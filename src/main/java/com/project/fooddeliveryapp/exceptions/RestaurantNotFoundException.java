package com.project.fooddeliveryapp.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    private final Long id;

    public RestaurantNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Restaurant with id " + id + " not found.";
    }
}
