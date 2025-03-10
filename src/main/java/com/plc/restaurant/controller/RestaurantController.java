package com.plc.restaurant.controller;

import com.plc.restaurant.controller.dto.RestaurantDto;
import com.plc.restaurant.controller.dto.UserDto;
import com.plc.restaurant.controller.dto.UserType;
import com.plc.restaurant.service.RestaurantService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDto register(@RequestBody RestaurantDto restaurantDto) {

        return restaurantService.save(restaurantDto);
    }
}
