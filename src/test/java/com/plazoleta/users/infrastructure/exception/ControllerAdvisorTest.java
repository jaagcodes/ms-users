package com.plazoleta.users.infrastructure.exceptionhandler;

import com.plazoleta.users.infrastructure.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerAdvisorTest {

    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {
        controllerAdvisor = new ControllerAdvisor();
    }

    @Test
    void handleInvalidEmailFormat_shouldReturnBadRequest() {
        ApplicationException ex = new InvalidEmailFormatException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email format", response.getBody().getMessage());
    }

    @Test
    void handleInvalidDocumentFormat_shouldReturnBadRequest() {
        ApplicationException ex = new InvalidDocumentFormatException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Document number must be numeric", response.getBody().getMessage());
    }

    @Test
    void handleInvalidPhoneFormat_shouldReturnBadRequest() {
        ApplicationException ex = new InvalidPhoneFormatException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Phone number format is invalid", response.getBody().getMessage());
    }

    @Test
    void handleInvalidPasswordFormat_shouldReturnBadRequest() {
        ApplicationException ex = new InvalidPasswordFormatException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password format is invalid", response.getBody().getMessage());
    }

    @Test
    void handleUnderageUser_shouldReturnBadRequest() {
        ApplicationException ex = new UnderAgeUserException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User must be over 18 years old", response.getBody().getMessage());
    }

    @Test
    void handleEmailAlreadyExists_shouldReturnConflict() {
        ApplicationException ex = new EmailAlreadyExistsException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email is already in use", response.getBody().getMessage());
    }

    @Test
    void handleDocumentNumberAlreadyExists_shouldReturnConflict() {
        ApplicationException ex = new DocumentNumberAlreadyExistsException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Document number is already in use", response.getBody().getMessage());
    }

    @Test
    void handleOwnerRoleNotFound_shouldReturnInternalServerError() {
        ApplicationException ex = new OwnerRoleNotFoundException();
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleApplicationExceptions(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("OWNER role not found in database", response.getBody().getMessage());
    }

    @Test
    void handleIllegalArgument_shouldReturnBadRequest() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleIllegalArgument(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().getMessage());
    }

    @Test
    void handleGenericException_shouldReturnInternalServerError() {
        Exception ex = new Exception("Something went wrong");
        ResponseEntity<ErrorResponse> response = controllerAdvisor.handleGenericException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error: Something went wrong", response.getBody().getMessage());
    }
}

