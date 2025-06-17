package com.plazoleta.users.infrastructure.configuration;

import com.plazoleta.users.domain.spi.IRolePersistencePort;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.domain.usecase.OwnerUseCase;
import com.plazoleta.users.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.plazoleta.users.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.plazoleta.users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.repository.IRoleRepository;
import com.plazoleta.users.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    private final BeanConfiguration configuration = new BeanConfiguration();

    @Test
    void userEntityMapperBeanShouldBeCreated() {
        assertNotNull(configuration.userEntityMapper());
    }

    @Test
    void roleEntityMapperBeanShouldBeCreated() {
        assertNotNull(configuration.roleEntityMapper());
    }

    @Test
    void userPersistencePortBeanShouldBeCreated() {
        IUserRepository userRepository = mock(IUserRepository.class);
        UserEntityMapper mapper = new UserEntityMapper();
        assertTrue(configuration.userPersistencePort(userRepository, mapper) instanceof UserJpaAdapter);
    }

    @Test
    void rolePersistencePortBeanShouldBeCreated() {
        IRoleRepository roleRepository = mock(IRoleRepository.class);
        RoleEntityMapper mapper = new RoleEntityMapper();
        assertTrue(configuration.rolePersistencePort(roleRepository, mapper) instanceof RoleJpaAdapter);
    }

    @Test
    void passwordEncoderBeanShouldBeCreated() {
        assertNotNull(configuration.passwordEncoder());
        assertTrue(configuration.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    @Test
    void ownerUseCaseBeanShouldBeCreated() {
        IUserPersistencePort userPort = mock(IUserPersistencePort.class);
        IRolePersistencePort rolePort = mock(IRolePersistencePort.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        OwnerUseCase useCase = configuration.ownerUseCase(userPort, rolePort, encoder);
        assertNotNull(useCase);
        assertTrue(useCase instanceof OwnerUseCase);
    }
}
