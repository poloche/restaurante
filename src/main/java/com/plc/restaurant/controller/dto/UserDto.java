package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:00
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@RequiredArgsConstructor
@Data
public class UserDto extends PersonDto {


    private BillDto billing;

    public User toUser() {
        return User.
                builder()
                .name(this.getName())
                .address(getAddress())
                .billing(billing.toBill())
                .cellphone(getCellphone())
                .facebookId(getFacebookId())
                .userType(UserType.CLIENT.name())
                .location(getLocation().toString())
                .build();
    }
}