package com.plc.restaurant.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.plc.restaurant.model.Offer;
import com.plc.restaurant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDto {
    private int id;
    private String owner;
    private String cellphone;
    private String category;
    private LocationDTO location;
    private BillDto billing;
    private List<Offer> offers;

    public static RestaurantDto fromEntity(Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .owner(restaurant.getOwner())
                .category(restaurant.getCategory())
                .cellphone(restaurant.getCellphone())
                .build();
    }


    public Restaurant toRestaurant() {
        return Restaurant.builder()
                .category(category)
                .owner(owner)
                .cellphone(cellphone)
                .location(location.toString())
                .billing(billing.toBill())
                .build();
    }
}