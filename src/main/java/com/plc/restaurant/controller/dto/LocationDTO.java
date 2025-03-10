package com.plc.restaurant.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDTO {
    private String latitude;
    private String longitude;

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
