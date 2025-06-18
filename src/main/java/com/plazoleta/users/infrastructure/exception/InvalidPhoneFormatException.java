package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class InvalidPhoneFormatException extends ApplicationException {
    public InvalidPhoneFormatException() {
        super("Phone number format is invalid", HttpStatus.BAD_REQUEST);
    }
}
