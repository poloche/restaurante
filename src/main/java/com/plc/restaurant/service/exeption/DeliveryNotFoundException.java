package com.plc.restaurant.service.exeption;

public class DeliveryNotFoundException extends Exception {
    public DeliveryNotFoundException(Integer deliveryId) {
        super("Delivery with Id: " + deliveryId + " does not exists");
    }
}
