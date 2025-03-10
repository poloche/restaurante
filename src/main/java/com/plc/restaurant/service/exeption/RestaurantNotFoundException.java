package com.plc.restaurant.service.exeption;

public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException(Integer restaurantId) {
        super("A Restaurant with Id:" + restaurantId + ", could not be found");
    }
}
