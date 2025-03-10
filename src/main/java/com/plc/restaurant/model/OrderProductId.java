package com.plc.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@Getter
@Setter
@Embeddable
public class OrderProductId implements Serializable {
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Column(name = "product", nullable = false)
    private Integer productId;
}