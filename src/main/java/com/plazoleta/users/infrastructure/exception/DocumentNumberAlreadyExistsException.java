package com.plazoleta.users.infrastructure.exception;

public class DocumentNumberAlreadyExistsException extends RuntimeException {
    public DocumentNumberAlreadyExistsException() {
        super("Document number is already in use");
    }
}
