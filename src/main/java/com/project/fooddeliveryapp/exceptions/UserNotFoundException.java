package com.project.fooddeliveryapp.exceptions;

public class UserNotFoundException extends RuntimeException {
//    throw new UserNotFoundException(name);
    private String name;

    public UserNotFoundException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "User " + name + "not found.";
    }
}
