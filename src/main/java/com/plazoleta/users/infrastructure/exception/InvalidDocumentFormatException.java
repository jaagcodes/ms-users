package com.plazoleta.users.infrastructure.exception;

public class InvalidDocumentFormatException extends RuntimeException {
    public InvalidDocumentFormatException() {
        super("Document number must be numeric");
    }
}
