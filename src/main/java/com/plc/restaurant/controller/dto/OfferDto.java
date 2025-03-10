package com.plc.restaurant.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.plc.restaurant.model.Offer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDto {
    private int offerId;
    private LocalDate date;
    private List<Integer> products;
    private List<ProductDto> detailedProducts;
    private RestaurantDto restaurant;

    public Offer toOffer() {
        return Offer.builder()
                .date(date)
                .build();
    }
}
