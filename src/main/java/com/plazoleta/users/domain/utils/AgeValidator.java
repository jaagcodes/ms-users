package com.plazoleta.users.domain.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator {

    private static final int LEGAL_AGE = 18;

    public static boolean isOfLegalAge(LocalDate birthDate) {
        if (birthDate == null) return false;
        return Period.between(birthDate, LocalDate.now()).getYears() >= LEGAL_AGE;
    }
}
