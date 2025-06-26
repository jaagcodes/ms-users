package com.plazoleta.users.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.handler.IUserHandler;
import com.plazoleta.users.infrastructure.exception.EmailAlreadyExistsException;
import com.plazoleta.users.infrastructure.exception.InvalidEmailFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRestController.class)
@Import(UsersRestControllerTest.TestConfig.class)
class UsersRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserHandler ownerHandler;

    private CreateOwnerRequest validRequest;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public IUserHandler ownerHandler() {
            return Mockito.mock(IUserHandler.class);
        }
    }

    @BeforeEach
    void setup() {
        validRequest = new CreateOwnerRequest();
        validRequest.setFirstName("Jose");
        validRequest.setLastName("Arrautt");
        validRequest.setDocumentNumber("123456");
        validRequest.setPhone("+573001112233");
        validRequest.setBirthDate(LocalDate.of(1990, 1, 1));
        validRequest.setEmail("jose@example.com");
        validRequest.setPassword("securePass");

        // 🧹 Resetear mocks para evitar invocaciones múltiples entre tests
        reset(ownerHandler);
    }

    @Test
    void shouldReturn201WhenOwnerIsCreatedSuccessfully() throws Exception {
        mockMvc.perform(post("/owners")
                        .with(csrf())
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated());

        verify(ownerHandler).createOwner(argThat(actualRequest ->
                actualRequest.getFirstName().equals(validRequest.getFirstName()) &&
                        actualRequest.getLastName().equals(validRequest.getLastName()) &&
                        actualRequest.getDocumentNumber().equals(validRequest.getDocumentNumber()) &&
                        actualRequest.getPhone().equals(validRequest.getPhone()) &&
                        actualRequest.getBirthDate().equals(validRequest.getBirthDate()) &&
                        actualRequest.getEmail().equals(validRequest.getEmail()) &&
                        actualRequest.getPassword().equals(validRequest.getPassword())
        ));
    }

    @Test
    void shouldReturn400WhenEmailFormatIsInvalid() throws Exception {
        doThrow(new InvalidEmailFormatException()).when(ownerHandler)
                .createOwner(argThat(req -> req.getEmail().equals(validRequest.getEmail())));

        mockMvc.perform(post("/owners")
                        .with(csrf())
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn409WhenEmailAlreadyExists() throws Exception {
        doThrow(new EmailAlreadyExistsException()).when(ownerHandler)
                .createOwner(argThat(req -> req.getEmail().equals(validRequest.getEmail())));

        mockMvc.perform(post("/owners")
                        .with(csrf())
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isConflict());
    }
}
