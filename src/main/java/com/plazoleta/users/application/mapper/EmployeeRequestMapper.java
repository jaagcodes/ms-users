package com.plazoleta.users.application.mapper;

import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRequestMapper {

    public User toUser(CreateEmployeeRequest request) {
        return new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getDocument(),
                request.getPhone(),
                null,
                request.getEmail(),
                request.getPassword(),
                null, // El rol se asigna dentro del use case
                request.getRestaurantId()
        );
    }
}
