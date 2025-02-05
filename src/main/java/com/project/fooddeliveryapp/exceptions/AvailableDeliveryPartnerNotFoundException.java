package com.project.fooddeliveryapp.exceptions;

public class AvailableDeliveryPartnerNotFoundException extends RuntimeException {

    public AvailableDeliveryPartnerNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "No Delivery Partner available.";
    }
}
