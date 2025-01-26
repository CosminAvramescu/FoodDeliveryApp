package com.project.fooddeliveryapp.exceptions;

public class UserNotFoundException extends RuntimeException {
    private Long id;

    public UserNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Customer with id " + id + " not found.";
    }
}
