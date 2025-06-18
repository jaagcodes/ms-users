package com.plazoleta.users.infrastructure.output.jpa.mapper;

import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setDocumentNumber(user.getDocumentNumber());
        entity.setPhone(user.getPhoneNumber());
        entity.setBirthDate(user.getBirthDate());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(new RoleEntityMapper().toEntity(user.getRole()));
        return entity;
    }

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDocumentNumber(),
                entity.getPhone(),
                entity.getBirthDate(),
                entity.getEmail(),
                entity.getPassword(),
                new RoleEntityMapper().toDomain(entity.getRole())
        );
    }
}
