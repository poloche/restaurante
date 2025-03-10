package com.plc.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:02
 */
@Entity
@Table(name = "bill")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Bill {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "billing")
    private List<User> users;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "creditCardNumber = " + creditCardNumber + ", " +
                "billingAddress = " + billingAddress + ", " +
                "paymentMethod = " + paymentMethod + ")";
    }
}