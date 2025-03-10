package com.plc.restaurant.controller;

import com.plc.restaurant.controller.dto.DeliveryDto;
import com.plc.restaurant.controller.dto.UserType;
import com.plc.restaurant.service.DeliveryService;
import com.plc.restaurant.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryDto register(@RequestBody DeliveryDto deliveryDto) {
        deliveryDto.setUserType(UserType.DELIVERY);
        return deliveryService.save(deliveryDto);
    }
}
