package com.plazoleta.users.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void shouldCreateAndAccessRoleFieldsCorrectly() {
        // Arrange
        Long id = 1L;
        String name = "OWNER";
        String description = "Role for restaurant owners";

        // Act
        Role role = new Role(id, name, description);

        // Assert
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
        assertEquals(description, role.getDescription());

        // Setters test
        role.setId(2L);
        role.setName("ADMIN");
        role.setDescription("Admin role");

        assertEquals(2L, role.getId());
        assertEquals("ADMIN", role.getName());
        assertEquals("Admin role", role.getDescription());
    }
}
