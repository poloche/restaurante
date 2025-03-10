package com.plc.restaurant.controller;

import com.plc.restaurant.controller.dto.ProductDto;
import com.plc.restaurant.service.ProductService;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurant/{restaurantId}/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto register(@PathVariable Integer restaurantId, @RequestBody ProductDto productDto) throws RestaurantNotFoundException {
        return productService.save(restaurantId, productDto);
    }
}
