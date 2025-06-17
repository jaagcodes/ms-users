package com.plazoleta.users.infrastructure.exception;

public class OwnerRoleNotFoundException extends RuntimeException {
    public OwnerRoleNotFoundException() {
        super("OWNER role not found in database");
    }
}
