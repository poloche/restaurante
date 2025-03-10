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
public class ProductOfferId implements Serializable {
    @Column(name = "offer_id", nullable = false)
    private Integer offerId;
    @Column(name = "product_id", nullable = false)
    private Integer productId;
}