package com.plc.restaurant.controller;

import com.plc.restaurant.controller.dto.OfferCreationDto;
import com.plc.restaurant.controller.dto.OfferDto;
import com.plc.restaurant.service.OfferService;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("restaurant/{restaurantId}/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OfferCreationDto register(@PathVariable Integer restaurantId, @RequestBody OfferDto offerDto) throws RestaurantNotFoundException {
        return offerService.save(restaurantId, offerDto);
    }

    @GetMapping()
    public OfferDto register(@PathVariable Integer restaurantId, @RequestParam LocalDate date) throws RestaurantNotFoundException {
        return offerService.getOffer(restaurantId, date);
    }
}
