package com.plc.restaurant.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderCreationDto {
    private OrderDto order;
    private List<Integer> productsNotAvailable;

}
