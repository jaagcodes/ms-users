package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends ApplicationException {
    public EmailAlreadyExistsException() {
        super("Email is already in use", HttpStatus.CONFLICT);
    }
}
