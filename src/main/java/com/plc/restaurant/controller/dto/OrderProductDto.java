package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.OrderProduct;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductDto {
    private int productId;
    private int quantity;

    public static OrderProductDto fromEntity(OrderProduct entity) {
        return OrderProductDto.builder()
                .productId(entity.getProduct().getId())
                .quantity(entity.getQuantity())
                .build();
    }
}
