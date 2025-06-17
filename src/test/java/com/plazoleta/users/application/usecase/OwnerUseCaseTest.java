package com.plazoleta.users.application.usecase;

import com.plazoleta.users.domain.model.Role;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.spi.IRolePersistencePort;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.domain.usecase.OwnerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OwnerUseCaseTest {

    private IUserPersistencePort userPersistencePort;
    private IRolePersistencePort rolePersistencePort;
    private BCryptPasswordEncoder passwordEncoder;
    private OwnerUseCase ownerUseCase;

    @BeforeEach
    void setUp() {
        userPersistencePort = mock(IUserPersistencePort.class);
        rolePersistencePort = mock(IRolePersistencePort.class);
        passwordEncoder = new BCryptPasswordEncoder();
        ownerUseCase = new OwnerUseCase(userPersistencePort, rolePersistencePort, passwordEncoder);
    }

    @Test
    void shouldCreateOwnerSuccessfully() {
        // Datos válidos
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789",
                "+573005698325",
                LocalDate.of(1990, 1, 1),
                "carlos.lopez@domain.com",
                "clave123",
                null
        );

        Role ownerRole = new Role(1L, "OWNER", "Propietario de restaurante");

        when(rolePersistencePort.findByName("OWNER")).thenReturn(ownerRole);
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocumentNumber(user.getDocumentNumber())).thenReturn(false);

        assertDoesNotThrow(() -> ownerUseCase.createOwner(user));
        verify(userPersistencePort, times(1)).saveUser(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenCustomerIsUnderLegalAge() {
        // Datos válidos
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789",
                "+573005698325",
                LocalDate.of(2010, 1, 1),
                "carlos.lopez@domain.com",
                "clave123",
                null
        );

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("User must be over 18 years old", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordFormatIsInvalid() {
        // Datos válidos
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789",
                "+573005698325",
                LocalDate.of(2003, 1, 1),
                "carlos.lopez@domain.com",
                null,
                null
        );

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocumentNumber(user.getDocumentNumber())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("Password format is invalid", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCustomerHasInvalidPhoneFormat() {
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789",
                "57300569832509",
                LocalDate.of(1990, 1, 1),
                "carlos.lopez@domain.com",
                "clave123",
                null
        );

        when(rolePersistencePort.findByName("OWNER")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("Phone number format is invalid", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCustomerHasInvalidEmailFormat() {
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789",
                "+573005698325",
                LocalDate.of(1990, 1, 1),
                "carlos.domain.com",
                "clave123",
                null
        );

        when(rolePersistencePort.findByName("OWNER")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCustomerHasInvalidDocumentNumberFormat() {
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789AB",
                "57300569832509",
                LocalDate.of(1990, 1, 1),
                "carlos.lopez@domain.com",
                "clave123",
                null
        );

        when(rolePersistencePort.findByName("OWNER")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("Document number must be numeric", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOwnerRoleNotFound() {
        User user = new User(
                null,
                "Carlos",
                "Lopez",
                "123456789",
                "+573005698325",
                LocalDate.of(1990, 1, 1),
                "carlos.lopez@domain.com",
                "clave123",
                null
        );

        when(rolePersistencePort.findByName("OWNER")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("OWNER role not found in database", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        User user = new User(null, "Luis", "Torres", "888999000", "3001112233",
                LocalDate.of(1992, 5, 10), "luis.torres@domain.com", null, null);

        Role ownerRole = new Role(1L, "OWNER", "Propietario de restaurante");

        when(rolePersistencePort.findByName("OWNER")).thenReturn(ownerRole);
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("Email is already in use", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDocumentAlreadyExists() {
        User user = new User(null, "Juan", "Martinez", "111222333", "3104455667",
                LocalDate.of(1991, 7, 20), "juan.martinez@domain.com", null, null);

        Role ownerRole = new Role(1L, "OWNER", "Propietario de restaurante");

        when(rolePersistencePort.findByName("OWNER")).thenReturn(ownerRole);
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocumentNumber(user.getDocumentNumber())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ownerUseCase.createOwner(user));
        assertEquals("Document number is already in use", exception.getMessage());
    }
}
