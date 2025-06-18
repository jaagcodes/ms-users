package com.plazoleta.users.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class UserRoleNotAssignedException extends ApplicationException{
    public UserRoleNotAssignedException(){
        super("User does not have role assigned", HttpStatus.NOT_FOUND);
    }
}
