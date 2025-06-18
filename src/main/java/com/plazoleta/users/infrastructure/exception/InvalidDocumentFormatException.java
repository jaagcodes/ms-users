package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class InvalidDocumentFormatException extends ApplicationException {
    public InvalidDocumentFormatException() {
        super("Document number must be numeric", HttpStatus.BAD_REQUEST);
    }
}
