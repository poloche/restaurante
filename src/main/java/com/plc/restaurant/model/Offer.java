package com.plc.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@Entity
@Table(name = "offer")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Offer {

    @Id
    @Column(name = "offer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "offer")
    private List<ProductOffer> products;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "offeringId = " + offerId + ", " +
                "date = " + date + ", " +
                "restaurantId = " + restaurant + ")";
    }
}