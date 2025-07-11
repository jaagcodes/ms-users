package com.plazoleta.users.application.handler;

import com.plazoleta.users.application.dto.CreateClientRequest;
import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.domain.model.User;

public interface IUserHandler {
    boolean isUserOwner(Long userId);
    void createOwner(CreateOwnerRequest request);
    void createEmployee(CreateEmployeeRequest request);
    User createClient(CreateClientRequest request);
    boolean isEmployeeOfRestaurant(Long employeeId, Long restaurantId);
    User findUserById(Long id);
}