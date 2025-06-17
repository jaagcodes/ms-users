package com.plazoleta.users.infrastructure.exception;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException() {
        super("Invalid email format");
    }
}
