package com.plazoleta.users.infrastructure.seed;

import com.plazoleta.users.infrastructure.output.jpa.entity.RoleEntity;
import com.plazoleta.users.infrastructure.output.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class RoleDataSeederTest {

    private IRoleRepository roleRepository;
    private RoleDataSeeder roleDataSeeder;

    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        roleDataSeeder = new RoleDataSeeder(roleRepository);
    }

    @Test
    void shouldInsertBothRolesWhenNotPresent() {
        when(roleRepository.findByName("OWNER")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.empty());

        roleDataSeeder.seedRoles();

        verify(roleRepository).save(argThat(role ->
                role.getName().equals("OWNER") &&
                        role.getDescription().equals("Propietario de restaurante")
        ));
        verify(roleRepository).save(argThat(role ->
                role.getName().equals("ADMIN") &&
                        role.getDescription().equals("Administrador del sistema")
        ));
    }

    @Test
    void shouldInsertOnlyAdminIfOwnerExists() {
        when(roleRepository.findByName("OWNER")).thenReturn(Optional.of(new RoleEntity()));
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.empty());

        roleDataSeeder.seedRoles();

        verify(roleRepository, never()).save(argThat(role -> role.getName().equals("OWNER")));
        verify(roleRepository).save(argThat(role -> role.getName().equals("ADMIN")));
    }

    @Test
    void shouldInsertOnlyOwnerIfAdminExists() {
        when(roleRepository.findByName("OWNER")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(new RoleEntity()));

        roleDataSeeder.seedRoles();

        verify(roleRepository).save(argThat(role -> role.getName().equals("OWNER")));
        verify(roleRepository, never()).save(argThat(role -> role.getName().equals("ADMIN")));
    }

    @Test
    void shouldNotInsertAnyRolesIfBothExist() {
        when(roleRepository.findByName("OWNER")).thenReturn(Optional.of(new RoleEntity()));
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(new RoleEntity()));

        roleDataSeeder.seedRoles();

        verify(roleRepository, never()).save(any(RoleEntity.class));
    }
}
