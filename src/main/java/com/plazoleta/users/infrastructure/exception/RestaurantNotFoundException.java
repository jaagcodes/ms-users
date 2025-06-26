package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class RestaurantNotFoundException extends ApplicationException{
    public RestaurantNotFoundException() {
        super("Restaurant not found error", HttpStatus.NOT_FOUND);
    }
}
