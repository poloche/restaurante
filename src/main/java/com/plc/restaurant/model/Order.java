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
@Table(name = "`order`")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "client")
    private User client;

    @ManyToOne()
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProduct;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "orderId = " + orderId + ", " +
                "date = " + date + ", " +
                "client = " + client + ", " +
                "restaurant = " + restaurant + ", " +
                "status = " + status + ", " +
                "total = " + total + ")";
    }
}