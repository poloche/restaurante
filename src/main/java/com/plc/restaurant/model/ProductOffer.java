package com.plc.restaurant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@Entity
@Table(name = "product_offer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProductOffer {
    @EmbeddedId
    private ProductOfferId id;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "offer_id", insertable = false, updatable = false)
    private Offer offer;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}