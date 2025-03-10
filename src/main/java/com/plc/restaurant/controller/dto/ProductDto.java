package com.plc.restaurant.controller.dto;

import com.plc.restaurant.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private String image;

    public Product toProduct() {
        return Product.builder()
                .name(name)
                .description(description)
                .image(image)
                .build();
    }
}
