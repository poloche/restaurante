package com.plc.restaurant.service;

import com.plc.restaurant.controller.dto.ProductDto;
import com.plc.restaurant.model.Product;
import com.plc.restaurant.model.Restaurant;
import com.plc.restaurant.model.repository.ProductRepository;
import com.plc.restaurant.model.repository.RestaurantRepository;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    public ProductService(ProductRepository productRepository, RestaurantRepository restaurantRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ProductDto save(Integer restaurantId, ProductDto dto) throws RestaurantNotFoundException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFoundException(restaurantId);
        }
        Product product = dto.toProduct();
        productRepository.save(product);
        dto.setId(product.getId());
        return dto;
    }
}
