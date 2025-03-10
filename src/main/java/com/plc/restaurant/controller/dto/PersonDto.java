package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@ToString
@RequiredArgsConstructor
@Data
public class PersonDto {

    private int id;

    private String name;

    private String address;

    private String cellphone;

    private String facebookId;

    private LocationDTO location;

    private BillDto billing;
    private VehicleDto vehicle;

    private UserType userType;

    public User toUser() {
        User.UserBuilder builder = User.builder();
        builder.name(name)
                .address(address)
                .billing(billing.toBill())
                .cellphone(cellphone)
                .facebookId(facebookId);
        if (userType.equals(UserType.DELIVERY)) {
            builder.vehicle(vehicle.toVehicle());
        } else {
            builder.location(location.toString());
        }
        return
                builder
                        .userType(userType.name().toLowerCase())
                        .build();
    }
}