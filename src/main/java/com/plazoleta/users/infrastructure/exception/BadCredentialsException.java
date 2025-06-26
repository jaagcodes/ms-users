package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends ApplicationException{
    public BadCredentialsException() {
        super("Invalid credentials", HttpStatus.BAD_REQUEST);
    }
}
