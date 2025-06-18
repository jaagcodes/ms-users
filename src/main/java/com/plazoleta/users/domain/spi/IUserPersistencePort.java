package com.plazoleta.users.domain.spi;

import com.plazoleta.users.domain.model.User;

public interface IUserPersistencePort {
    User saveUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
    User findById(Long id);
}
