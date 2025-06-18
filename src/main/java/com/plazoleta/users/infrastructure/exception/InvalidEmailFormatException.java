package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailFormatException extends ApplicationException {
    public InvalidEmailFormatException() {
        super("Invalid email format", HttpStatus.BAD_REQUEST);
    }
}
