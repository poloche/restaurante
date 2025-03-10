package com.plc.restaurant.service.exeption;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Integer orderId) {
        super("A Order with Id:" + orderId + ", was not found.");
    }
}
