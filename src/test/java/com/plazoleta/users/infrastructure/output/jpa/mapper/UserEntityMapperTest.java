package com.plazoleta.users.infrastructure.output.jpa.mapper;

import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.infrastructure.output.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperTest {

    private UserEntityMapper userEntityMapper;

    @BeforeEach
    void setUp() {
        userEntityMapper = new UserEntityMapper();
    }

    @Test
    void shouldMapUserToUserEntityWithoutRole() {
        User user = new User(
                1L,
                "Jose",
                "Arrautt",
                "123456789",
                "+573001112233",
                LocalDate.of(1990, 1, 1),
                "jose@example.com",
                "securePass",
                null // sin rol
        );

        UserEntity entity = userEntityMapper.toEntity(user);

        assertNotNull(entity);
        assertNull(entity.getId()); // no se setea en toEntity()
        assertEquals(user.getFirstName(), entity.getFirstName());
        assertEquals(user.getLastName(), entity.getLastName());
        assertEquals(user.getDocumentNumber(), entity.getDocumentNumber());
        assertEquals(user.getPhoneNumber(), entity.getPhone());
        assertEquals(user.getBirthDate(), entity.getBirthDate());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    void shouldMapUserEntityToUserWithNullRole() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setFirstName("Jose");
        entity.setLastName("Arrautt");
        entity.setDocumentNumber("123456789");
        entity.setPhone("+573001112233");
        entity.setBirthDate(LocalDate.of(1990, 1, 1));
        entity.setEmail("jose@example.com");
        entity.setPassword("securePass");

        User user = userEntityMapper.toDomain(entity);

        assertNotNull(user);
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getFirstName(), user.getFirstName());
        assertEquals(entity.getLastName(), user.getLastName());
        assertEquals(entity.getDocumentNumber(), user.getDocumentNumber());
        assertEquals(entity.getPhone(), user.getPhoneNumber());
        assertEquals(entity.getBirthDate(), user.getBirthDate());
        assertEquals(entity.getEmail(), user.getEmail());
        assertEquals(entity.getPassword(), user.getPassword());
        assertNull(user.getRole());
    }
}
