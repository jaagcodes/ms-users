package com.plazoleta.users.infrastructure.configuration;

import com.plazoleta.users.domain.api.IAuthServicePort;
import com.plazoleta.users.domain.api.IOwnerServicePort;
import com.plazoleta.users.domain.api.ISecurityServicePort;
import com.plazoleta.users.domain.api.IUserServicePort;
import com.plazoleta.users.domain.spi.IRestaurantClientPort;
import com.plazoleta.users.domain.usecase.AuthUseCase;
import com.plazoleta.users.domain.usecase.OwnerUseCase;
import com.plazoleta.users.domain.spi.IRolePersistencePort;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.domain.usecase.UserUseCase;
import com.plazoleta.users.infrastructure.output.feign.adapter.RestaurantClientAdapter;
import com.plazoleta.users.infrastructure.output.feign.client.RestaurantClient;
import com.plazoleta.users.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.plazoleta.users.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.plazoleta.users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.repository.IRoleRepository;
import com.plazoleta.users.infrastructure.output.jpa.repository.IUserRepository;
import com.plazoleta.users.infrastructure.security.adapter.SecurityServiceAdapter;
import com.plazoleta.users.infrastructure.security.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    public RoleEntityMapper roleEntityMapper() {
        return new RoleEntityMapper();
    }

    @Bean
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(IRoleRepository roleRepository, RoleEntityMapper roleEntityMapper) {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IOwnerServicePort ownerServicePort(IUserPersistencePort userPersistencePort,
                                              IRolePersistencePort rolePersistencePort,
                                              BCryptPasswordEncoder passwordEncoder) {
        return new OwnerUseCase(userPersistencePort, rolePersistencePort, passwordEncoder);
    }

    @Bean
    public ISecurityServicePort securityServicePort() {
        return new SecurityServiceAdapter();
    }

    @Bean
    public IRestaurantClientPort restaurantClientPort(RestaurantClient restaurantClient) {
        return new RestaurantClientAdapter(restaurantClient);
    }

    @Bean
    public IUserServicePort userServicePort(
            IUserPersistencePort userPersistencePort,
            IRestaurantClientPort restaurantClientPort,
            IRolePersistencePort rolePersistencePort,
            ISecurityServicePort securityServicePort
    ) {
        return new UserUseCase(userPersistencePort, restaurantClientPort, rolePersistencePort, securityServicePort);
    }

    @Bean
    public IAuthServicePort authServicePort(
            IUserPersistencePort userPersistencePort,
            JwtUtil jwtUtil,
            BCryptPasswordEncoder passwordEncoder
    ) {
        return new AuthUseCase(userPersistencePort, jwtUtil, passwordEncoder);
    }

}
