package com.plc.restaurant.controller;

import com.plc.restaurant.controller.dto.PersonDto;
import com.plc.restaurant.controller.dto.UserDto;
import com.plc.restaurant.controller.dto.UserType;
import com.plc.restaurant.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto register(@RequestBody UserDto user) {
        user.setUserType(UserType.CLIENT);
        return userService.save(user);
    }
}
