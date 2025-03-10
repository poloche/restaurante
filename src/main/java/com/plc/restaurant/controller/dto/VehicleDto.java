package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.Vehicle;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleDto {
    private int id;
    private String plate;

    public Vehicle toVehicle() {
        return Vehicle.builder()
                .plate(plate)
                .build();
    }
}
