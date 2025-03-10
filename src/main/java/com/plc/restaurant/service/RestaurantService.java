package com.plc.restaurant.service;

import com.plc.restaurant.controller.dto.RestaurantDto;
import com.plc.restaurant.model.Bill;
import com.plc.restaurant.model.Restaurant;
import com.plc.restaurant.model.repository.BillRepository;
import com.plc.restaurant.model.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final BillRepository billRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, BillRepository billRepository) {
        this.restaurantRepository = restaurantRepository;
        this.billRepository = billRepository;
    }

    public RestaurantDto save(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantDto.toRestaurant();
        Bill bill = restaurant.getBilling();
        billRepository.save(bill);
        restaurant.setBilling(bill);
        restaurantRepository.save(restaurant);
        restaurantDto.setId(restaurant.getId());
        return restaurantDto;
    }
}
