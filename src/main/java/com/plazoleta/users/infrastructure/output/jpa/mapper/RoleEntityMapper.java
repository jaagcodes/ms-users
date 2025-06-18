package com.plazoleta.users.infrastructure.output.jpa.mapper;

import com.plazoleta.users.domain.model.Role;
import com.plazoleta.users.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapper {

    public Role toDomain(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return new Role(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getDescription()
        );
    }

    public RoleEntity toEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());
        roleEntity.setDescription(role.getDescription());
        return roleEntity;
    }
}
