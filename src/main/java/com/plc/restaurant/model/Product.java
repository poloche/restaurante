package com.plc.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@Entity
@Table(name = "product")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "cost")
    private double cost;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProduct;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + name + ", " +
                "description = " + description + ", " +
                "image = " + image + ")";
    }
}