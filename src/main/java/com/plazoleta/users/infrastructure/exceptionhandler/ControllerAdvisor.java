package com.plazoleta.users.infrastructure.exceptionhandler;

import com.plazoleta.users.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationExceptions(ApplicationException ex) {
        return buildResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, status));
    }
}
