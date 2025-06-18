package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class UnderAgeUserException extends ApplicationException {
    public UnderAgeUserException() {
        super("User must be over 18 years old", HttpStatus.BAD_REQUEST);
    }
}
