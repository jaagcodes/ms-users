package com.plazoleta.users.domain.utils;

public enum RoleEnum {
    OWNER("OWNER"),
    ADMIN("ADMIN"),
    CLIENT("CLIENT"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
