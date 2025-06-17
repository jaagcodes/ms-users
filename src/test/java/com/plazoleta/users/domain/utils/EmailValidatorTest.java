package com.plazoleta.users.domain.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void shouldReturnTrueForValidEmail() {
        assertTrue(EmailValidator.isValid("test@example.com"));
    }

    @Test
    void shouldReturnFalseForInvalidEmail() {
        assertFalse(EmailValidator.isValid("invalid-email"));
    }

    @Test
    void shouldReturnFalseWhenEmailIsNull() {
        assertFalse(EmailValidator.isValid(null));
    }
}
