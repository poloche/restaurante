package com.plc.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@Entity
@Table(name = "`user`")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "celphone")
    private String cellphone;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "billing")
    private Bill billing;

    @ManyToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @Column(name = "user_type", nullable = false)
    private String userType;
}