package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordFormatException extends ApplicationException {
    public InvalidPasswordFormatException() {
        super("Password format is invalid", HttpStatus.BAD_REQUEST);
    }
}
