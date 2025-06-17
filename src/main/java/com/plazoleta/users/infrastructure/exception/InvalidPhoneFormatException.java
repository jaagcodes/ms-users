package com.plazoleta.users.infrastructure.exception;

public class InvalidPhoneFormatException extends RuntimeException {
    public InvalidPhoneFormatException() {
        super("Phone number format is invalid");
    }
}
