package com.plazoleta.users.domain.spi;

import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.infrastructure.output.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {
    User saveUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
    User findById(Long id);
    Optional<User> findByEmail(String email);
}
