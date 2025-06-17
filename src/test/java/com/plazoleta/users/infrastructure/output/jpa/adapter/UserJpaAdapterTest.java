package com.plazoleta.users.infrastructure.output.jpa.adapter;

import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.infrastructure.output.jpa.entity.UserEntity;
import com.plazoleta.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(
                1L,
                "Jose",
                "Arrautt",
                "123456",
                "+573001112233",
                LocalDate.of(1990, 1, 1),
                "jose@example.com",
                "securePass",
                null
        );

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Jose");
        userEntity.setLastName("Arrautt");
        userEntity.setDocumentNumber("123456");
        userEntity.setPhone("+573001112233");
        userEntity.setBirthDate(LocalDate.of(1990, 1, 1));
        userEntity.setEmail("jose@example.com");
        userEntity.setPassword("securePass");
    }

    @Test
    void shouldSaveUserSuccessfully() {
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        User savedUser = userJpaAdapter.saveUser(user);

        assertEquals(user, savedUser);
        verify(userRepository).save(userEntity);
    }

    @Test
    void shouldReturnTrueIfEmailExists() {
        when(userRepository.existsByEmail("jose@example.com")).thenReturn(true);

        boolean exists = userJpaAdapter.existsByEmail("jose@example.com");

        assertTrue(exists);
        verify(userRepository).existsByEmail("jose@example.com");
    }

    @Test
    void shouldReturnTrueIfDocumentNumberExists() {
        when(userRepository.existsByDocumentNumber("123456")).thenReturn(true);

        boolean exists = userJpaAdapter.existsByDocumentNumber("123456");

        assertTrue(exists);
        verify(userRepository).existsByDocumentNumber("123456");
    }
}
