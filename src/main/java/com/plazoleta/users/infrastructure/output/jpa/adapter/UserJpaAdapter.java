package com.plazoleta.users.infrastructure.output.jpa.adapter;

import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.infrastructure.output.jpa.entity.UserEntity;
import com.plazoleta.users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity saved = userRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return userRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .map(userEntityMapper::toDomain)
                .orElse(null);
        /*
        Optional<UserEntity> entityOpt = userRepository.findById(id);
        System.out.println("findById(" + id + ") => present? " + entityOpt.isPresent());
        entityOpt.ifPresent(entity -> System.out.println("entity found: " + entity));
        return entityOpt.map(userEntityMapper::toDomain).orElse(null);
         */
    }
}
