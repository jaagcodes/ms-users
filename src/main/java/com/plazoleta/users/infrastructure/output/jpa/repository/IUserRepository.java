package com.plazoleta.users.infrastructure.output.jpa.repository;

import com.plazoleta.users.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
