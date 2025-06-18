package com.plazoleta.users.application.handler;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.mapper.OwnerRequestMapper;
import com.plazoleta.users.domain.api.IOwnerServicePort;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.infrastructure.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OwnerHandlerTest {

    @Mock
    private IOwnerServicePort ownerServicePort;

    @Mock
    private OwnerRequestMapper ownerRequestMapper;

    @InjectMocks
    private OwnerHandler ownerHandler;

    private CreateOwnerRequest validRequest;
    private User mappedUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validRequest = new CreateOwnerRequest();
        validRequest.setFirstName("Jose");
        validRequest.setLastName("Arrautt");
        validRequest.setDocumentNumber("123456");
        validRequest.setPhone("+573001112233");
        validRequest.setBirthDate(LocalDate.of(1990, 1, 1));
        validRequest.setEmail("jose@example.com");
        validRequest.setPassword("securePass");

        mappedUser = new User(
                null,
                "Jose",
                "Arrautt",
                "123456",
                "+573001112233",
                validRequest.getBirthDate(),
                "jose@example.com",
                "securePass",
                null
        );
    }

    @Test
    void shouldCreateOwnerSuccessfully() {
        when(ownerRequestMapper.toUser(validRequest)).thenReturn(mappedUser);
        when(ownerServicePort.createOwner(mappedUser)).thenReturn(mappedUser); // ✅ uso correcto

        ownerHandler.createOwner(validRequest);

        verify(ownerServicePort).createOwner(mappedUser);
    }

    @Test
    void shouldThrowInvalidEmailFormatException() {
        when(ownerRequestMapper.toUser(validRequest)).thenReturn(mappedUser);
        when(ownerServicePort.createOwner(mappedUser)).thenThrow(new InvalidEmailFormatException());

        assertThrows(InvalidEmailFormatException.class, () -> ownerHandler.createOwner(validRequest));
    }

    @Test
    void shouldThrowEmailAlreadyExistsException() {
        when(ownerRequestMapper.toUser(validRequest)).thenReturn(mappedUser);
        when(ownerServicePort.createOwner(mappedUser)).thenThrow(new EmailAlreadyExistsException());

        assertThrows(EmailAlreadyExistsException.class, () -> ownerHandler.createOwner(validRequest));
    }

    @Test
    void shouldThrowOwnerRoleNotFoundException() {
        when(ownerRequestMapper.toUser(validRequest)).thenReturn(mappedUser);
        when(ownerServicePort.createOwner(mappedUser)).thenThrow(new OwnerRoleNotFoundException());

        assertThrows(OwnerRoleNotFoundException.class, () -> ownerHandler.createOwner(validRequest));
    }
}
