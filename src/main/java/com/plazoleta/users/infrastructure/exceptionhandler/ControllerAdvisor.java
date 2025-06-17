package com.plazoleta.users.infrastructure.exceptionhandler;

import com.plazoleta.users.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormat() {
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid email format");
    }

    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordFormat() {
        return buildResponse(HttpStatus.BAD_REQUEST, "Password format is invalid");
    }

    @ExceptionHandler(InvalidDocumentFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDocumentFormat() {
        return buildResponse(HttpStatus.BAD_REQUEST, "Document number must be numeric");
    }

    @ExceptionHandler(InvalidPhoneFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPhoneFormat() {
        return buildResponse(HttpStatus.BAD_REQUEST, "Phone number format is invalid");
    }

    @ExceptionHandler(UnderAgeUserException.class)
    public ResponseEntity<ErrorResponse> handleUnderageUser() {
        return buildResponse(HttpStatus.BAD_REQUEST, "User must be over 18 years old");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists() {
        return buildResponse(HttpStatus.CONFLICT, "Email is already in use");
    }

    @ExceptionHandler(DocumentNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDocumentNumberAlreadyExists() {
        return buildResponse(HttpStatus.CONFLICT, "Document number is already in use");
    }

    @ExceptionHandler(OwnerRoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOwnerRoleNotFound() {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "OWNER role not found in database");
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
