package com.plazoleta.users.domain.utils;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public static boolean isValid(String email) {
        return email != null && EMAIL_REGEX.matcher(email).matches();
    }
}
