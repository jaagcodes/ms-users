package com.plazoleta.users.application.handler;

import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.application.dto.CreateOwnerRequest;

public interface IUserHandler {
    boolean isUserOwner(Long userId);
    void createOwner(CreateOwnerRequest request);
    void createEmployee(CreateEmployeeRequest request);
}