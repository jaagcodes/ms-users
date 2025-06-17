package com.plazoleta.users.domain.usecase;

import com.plazoleta.users.domain.model.Role;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.spi.IRolePersistencePort;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.domain.utils.AgeValidator;
import com.plazoleta.users.domain.utils.EmailValidator;
import com.plazoleta.users.infrastructure.exception.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class OwnerUseCase {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;

    public OwnerUseCase(IUserPersistencePort userPersistencePort,
                        IRolePersistencePort rolePersistencePort,
                        BCryptPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    public User createOwner(User user) {
        // Validar email
        if (!EmailValidator.isValid(user.getEmail())) {
            throw new InvalidEmailFormatException();
        }

        // Validar documento numérico
        if (!user.getDocumentNumber().matches("\\d+")) {
            throw new InvalidDocumentFormatException();
        }

        // Validar teléfono (máx 13, con opción de +)
        if (user.getPhoneNumber().length() > 13 || !user.getPhoneNumber().matches("^\\+?\\d{1,13}$")) {
            throw new InvalidPhoneFormatException();
        }

        // Validar mayoría de edad
        if (!AgeValidator.isOfLegalAge(user.getBirthDate())) {
            throw new UnderAgeUserException();
        }

        // Verificar unicidad
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (userPersistencePort.existsByDocumentNumber(user.getDocumentNumber())) {
            throw new DocumentNumberAlreadyExistsException();
        }

        // Encriptar clave
        if (user.getPassword() == null) {
            throw new InvalidPasswordFormatException();
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        // Obtener rol OWNER
        Role ownerRole = rolePersistencePort.findByName("OWNER");
        if (ownerRole == null) {
            throw new OwnerRoleNotFoundException();
        }

        // Crear nuevo usuario con datos finales
        User newUser = new User(
                null,
                user.getFirstName(),
                user.getLastName(),
                user.getDocumentNumber(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.getEmail(),
                encryptedPassword,
                ownerRole
        );

        return userPersistencePort.saveUser(newUser);
    }
}
