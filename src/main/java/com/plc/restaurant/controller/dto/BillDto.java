package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.Bill;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author paolo.lizarazu
 * Create at 2025-03-08 08:02
 */
@Data
@ToString
@RequiredArgsConstructor
public class BillDto {
    private int id;

    private String creditCardNumber;

    private String billingAddress;

    private String paymentMethod;

    public Bill toDto() {
        return null;
    }

    public Bill toBill() {
        return Bill.builder()
                .billingAddress(billingAddress)
                .creditCardNumber(creditCardNumber)
                .paymentMethod(paymentMethod)
                .build();
    }
}