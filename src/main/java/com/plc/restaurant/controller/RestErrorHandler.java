package com.plc.restaurant.controller;

import com.plc.restaurant.service.exeption.ClientExceptionNotFound;
import com.plc.restaurant.service.exeption.OrderNotFoundException;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<String> handleException(RestaurantNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ClientExceptionNotFound.class,
            OrderNotFoundException.class,
            ClientExceptionNotFound.class})
    public ResponseEntity<String> notFoundException(ClientExceptionNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}