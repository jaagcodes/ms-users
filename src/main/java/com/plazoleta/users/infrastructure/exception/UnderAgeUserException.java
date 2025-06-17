package com.plazoleta.users.infrastructure.exception;

public class UnderAgeUserException extends RuntimeException {
    public UnderAgeUserException() {
        super("User must be over 18 years old");
    }
}
