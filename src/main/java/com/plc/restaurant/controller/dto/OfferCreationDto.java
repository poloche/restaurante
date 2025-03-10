package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.Offer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OfferCreationDto {
    private OfferDto offerDto;
    private List<Integer> productsNotAvailable;

}
