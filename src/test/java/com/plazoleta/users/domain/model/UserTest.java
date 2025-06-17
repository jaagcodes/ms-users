package com.plazoleta.users.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateAndAccessUserFieldsCorrectly() {
        // Arrange
        Long id = 1L;
        String firstName = "Jose";
        String lastName = "Arrautt";
        String documentNumber = "123456789";
        String phoneNumber = "+573001112233";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        String email = "jose@example.com";
        String password = "securePass";
        Role role = new Role(1L, "OWNER", "Propietario");

        // Act
        User user = new User(id, firstName, lastName, documentNumber, phoneNumber, birthDate, email, password, role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(documentNumber, user.getDocumentNumber());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());

        // Test setters
        user.setId(2L);
        user.setFirstName("Ana");
        user.setLastName("Perez");
        user.setDocumentNumber("987654321");
        user.setPhoneNumber("3111111111");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setEmail("ana@example.com");
        user.setPassword("1234");
        user.setRole(new Role(2L, "ADMIN", "Admin role"));

        assertEquals(2L, user.getId());
        assertEquals("Ana", user.getFirstName());
        assertEquals("Perez", user.getLastName());
        assertEquals("987654321", user.getDocumentNumber());
        assertEquals("3111111111", user.getPhoneNumber());
        assertEquals(LocalDate.of(2000, 1, 1), user.getBirthDate());
        assertEquals("ana@example.com", user.getEmail());
        assertEquals("1234", user.getPassword());
        assertEquals("ADMIN", user.getRole().getName());
    }
}
