package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class DocumentNumberAlreadyExistsException extends ApplicationException {
    public DocumentNumberAlreadyExistsException() {
        super("Document number is already in use", HttpStatus.CONFLICT);
    }
}
