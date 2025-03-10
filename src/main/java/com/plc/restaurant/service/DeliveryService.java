package com.plc.restaurant.service;

import com.plc.restaurant.controller.dto.DeliveryDto;
import com.plc.restaurant.model.User;
import com.plc.restaurant.model.Vehicle;
import com.plc.restaurant.model.repository.UserRepository;
import com.plc.restaurant.model.repository.VehicleRepository;
import com.plc.restaurant.service.exeption.DeliveryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public DeliveryService(UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public DeliveryDto save(DeliveryDto personDto) {
        User user = personDto.toUser();

        Vehicle vehicle = personDto.getVehicle().toVehicle();
        user.setVehicle(vehicle);
        vehicleRepository.save(vehicle);
        user.setVehicle(vehicle);
        userRepository.save(user);
        personDto.setId(user.getId());
        return personDto;
    }

    public DeliveryDto update(Integer deliveryId, DeliveryDto deliveryDto) throws DeliveryNotFoundException {
        Optional<User> optionalUser = userRepository.findById(deliveryId);
        if (optionalUser.isEmpty()) {
            throw new DeliveryNotFoundException(deliveryId);
        }
        User delivery = deliveryDto.toUser();
        delivery.setId(optionalUser.get().getId());
        userRepository.save(delivery);
        deliveryDto.setId(optionalUser.get().getId());
        return deliveryDto;
    }
}
