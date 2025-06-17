package com.plazoleta.users.infrastructure.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email is already in use");
    }
}
