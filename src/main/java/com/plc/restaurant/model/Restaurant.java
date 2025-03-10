package com.plc.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@Entity
@Table(name = "restaurant")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Restaurant {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "cellphone", nullable = false)
    private String cellphone;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "billing")
    private Bill billing;

    @OneToMany(mappedBy = "restaurant")
    private List<Offer> offers;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "owner = " + owner + ", " +
                "cellphone = " + cellphone + ", " +
                "category = " + category + ", " +
                "location = " + location + ", " +
                "billing = " + billing + ")";
    }
}