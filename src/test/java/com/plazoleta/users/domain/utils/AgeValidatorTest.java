package com.plazoleta.users.domain.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AgeValidatorTest {

    @Test
    void shouldReturnTrueWhenUserIsOfLegalAge() {
        LocalDate birthDate = LocalDate.now().minusYears(20);
        assertTrue(AgeValidator.isOfLegalAge(birthDate));
    }

    @Test
    void shouldReturnFalseWhenUserIsUnderLegalAge() {
        LocalDate birthDate = LocalDate.now().minusYears(16);
        assertFalse(AgeValidator.isOfLegalAge(birthDate));
    }

    @Test
    void shouldReturnFalseWhenBirthDateIsNull() {
        assertFalse(AgeValidator.isOfLegalAge(null));
    }
}
