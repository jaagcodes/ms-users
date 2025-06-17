package com.plazoleta.users.infrastructure.output.jpa.adapter;

import com.plazoleta.users.domain.model.Role;
import com.plazoleta.users.infrastructure.output.jpa.entity.RoleEntity;
import com.plazoleta.users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleJpaAdapterTest {

    private IRoleRepository roleRepository;
    private RoleJpaAdapter roleJpaAdapter;

    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        roleJpaAdapter = new RoleJpaAdapter(roleRepository, new RoleEntityMapper()); // 👈 instancia real del mapper
    }

    @Test
    void shouldReturnRoleWhenFoundByName() {
        // Arrange
        String roleName = "OWNER";
        RoleEntity entity = new RoleEntity();
        entity.setId(1L);
        entity.setName(roleName);
        entity.setDescription("Role for owner");

        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(entity));

        // Act
        Role result = roleJpaAdapter.findByName(roleName);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("OWNER", result.getName());
        assertEquals("Role for owner", result.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {
        // Arrange
        String roleName = "ADMIN";
        when(roleRepository.findByName(roleName)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                roleJpaAdapter.findByName(roleName)
        );
        assertTrue(exception.getMessage().contains("Role not found: " + roleName));
    }
}
