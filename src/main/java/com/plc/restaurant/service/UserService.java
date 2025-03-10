package com.plc.restaurant.service;

import com.plc.restaurant.controller.dto.UserDto;
import com.plc.restaurant.model.Bill;
import com.plc.restaurant.model.User;
import com.plc.restaurant.model.repository.BillRepository;
import com.plc.restaurant.model.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BillRepository billRepository;


    public UserService(UserRepository userRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public UserDto save(UserDto personDto) {
        User user = personDto.toUser();

        Bill bill = user.getBilling();
        billRepository.save(bill);
        billRepository.flush();
        user.setBilling(bill);

        userRepository.save(user);
        personDto.setId(user.getId());
        return personDto;
    }
}
