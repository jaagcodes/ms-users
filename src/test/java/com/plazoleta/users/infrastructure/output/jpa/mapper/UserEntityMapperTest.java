package com.plazoleta.users.infrastructure.output.jpa.mapper;

import com.plazoleta.users.domain.model.Role;
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
    void shouldMapUserToUserEntityWithRole() {
        Role role = new Role(1L, "OWNER", "Description");
        User user = new User(
                1L,
                "Jose",
                "Arrautt",
                "123456789",
                "+573001112233",
                LocalDate.of(1990, 1, 1),
                "jose@example.com",
                "securePass",
                role // sin rol
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
}
