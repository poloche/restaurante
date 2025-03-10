package com.plc.restaurant.service.exeption;

public class ClientExceptionNotFound extends Exception {
    public ClientExceptionNotFound(Integer clientId) {
        super("A client with Id:" + clientId + " was not found");
    }
}
