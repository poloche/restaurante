package com.plc.restaurant.controller;

import com.plc.restaurant.controller.dto.OfferDto;
import com.plc.restaurant.controller.dto.OrderCreationDto;
import com.plc.restaurant.controller.dto.OrderDto;
import com.plc.restaurant.service.OrderService;
import com.plc.restaurant.service.exeption.ClientExceptionNotFound;
import com.plc.restaurant.service.exeption.OrderNotFoundException;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderCreationDto register(@RequestBody OrderDto orderDto) throws RestaurantNotFoundException, ClientExceptionNotFound {
        return orderService.save(orderDto);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Integer orderId) throws RestaurantNotFoundException, OrderNotFoundException {
        return orderService.getOrder(orderId);
    }
}
