package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class OwnerRoleNotFoundException extends ApplicationException {
    public OwnerRoleNotFoundException() {
        super("OWNER role not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
