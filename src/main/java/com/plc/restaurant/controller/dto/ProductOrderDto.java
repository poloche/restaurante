package com.plc.restaurant.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOrderDto {
    private int productId;
    private int quantity;
}
