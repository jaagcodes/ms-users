package com.plazoleta.users.application.mapper;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class OwnerRequestMapper {

    public User toUser(CreateOwnerRequest request) {
        return new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getDocumentNumber(),
                request.getPhone(),
                request.getBirthDate(),
                request.getEmail(),
                request.getPassword(),
                null // El rol se asigna dentro del use case
        );
    }
}

