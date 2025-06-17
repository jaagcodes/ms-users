package com.plazoleta.users.infrastructure.exception;

public class InvalidPasswordFormatException extends RuntimeException {
    public InvalidPasswordFormatException() {
        super("Password format is invalid");
    }
}
