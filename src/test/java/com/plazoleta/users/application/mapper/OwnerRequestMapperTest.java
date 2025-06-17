package com.plazoleta.users.application.mapper;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OwnerRequestMapperTest {

    private OwnerRequestMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new OwnerRequestMapper();
    }

    @Test
    void shouldMapCreateOwnerRequestToUserCorrectly() {
        // Arrange
        CreateOwnerRequest request = new CreateOwnerRequest();
        request.setFirstName("Jose");
        request.setLastName("Arrautt");
        request.setDocumentNumber("123456");
        request.setPhone("+573001112233");
        request.setBirthDate(LocalDate.of(1990, 1, 1));
        request.setEmail("jose@example.com");
        request.setPassword("securePass");

        // Act
        User result = mapper.toUser(request);

        // Assert
        assertNull(result.getId());
        assertEquals("Jose", result.getFirstName());
        assertEquals("Arrautt", result.getLastName());
        assertEquals("123456", result.getDocumentNumber());
        assertEquals("+573001112233", result.getPhoneNumber());
        assertEquals(LocalDate.of(1990, 1, 1), result.getBirthDate());
        assertEquals("jose@example.com", result.getEmail());
        assertEquals("securePass", result.getPassword());
        assertNull(result.getRole()); // Aún no se asigna
    }
}
