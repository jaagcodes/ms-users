package com.plazoleta.users.infrastructure.output.jpa.mapper;

import com.plazoleta.users.domain.model.Role;
import com.plazoleta.users.infrastructure.output.jpa.entity.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityMapperTest {

    private RoleEntityMapper roleEntityMapper;

    @BeforeEach
    void setUp() {
        roleEntityMapper = new RoleEntityMapper();
    }

    @Test
    void shouldMapRoleToEntity() {
        Role role = new Role(
                1L,
                "OWNER",
                "Role for restaurant owners"
        );

        RoleEntity entity = roleEntityMapper.toEntity(role);

        assertNotNull(entity);
        assertEquals(role.getId(), entity.getId());
        assertEquals(role.getName(), entity.getName());
        assertEquals(role.getDescription(), entity.getDescription());
    }

    @Test
    void shouldMapEntityToDomainRole() {
        RoleEntity entity = new RoleEntity();
        entity.setId(2L);
        entity.setName("ADMIN");
        entity.setDescription("Role for administrators");

        Role role = roleEntityMapper.toDomain(entity);

        assertNotNull(role);
        assertEquals(entity.getId(), role.getId());
        assertEquals(entity.getName(), role.getName());
        assertEquals(entity.getDescription(), role.getDescription());
    }
}
