package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.Order;
import com.plc.restaurant.service.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private int orderId;
    private int restaurantId;
    private int userId;
    private LocalDate date;
    private OrderStatus status;
    private List<OrderProductDto> products;

    public static OrderDto fromEntity(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .date(order.getDate())
                .status(OrderStatus.valueOf(order.getStatus()))
                .restaurantId(order.getRestaurant().getId())
                .userId(order.getClient().getId())
                .products(order.getOrderProduct().stream()
                        .map(OrderProductDto::fromEntity)
                        .toList()
                )
                .build();
    }

    public Order toOrder() {
        return Order.builder()

                .build();
    }
}
