package com.plazoleta.users.domain.usecase;

import com.plazoleta.users.domain.api.IUserServicePort;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.domain.utils.RoleEnum;
import com.plazoleta.users.infrastructure.exception.UserNotFoundException;
import com.plazoleta.users.infrastructure.exception.UserRoleNotAssignedException;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    public boolean isUserOwner(Long userId) {
        User user = userPersistencePort.findById(userId);
        System.out.println("user isUseOwner: " + user);
        if (user == null) {
            throw new UserNotFoundException();
        }

        if (user.getRole() == null) {
            throw new UserRoleNotAssignedException();
        }

        System.out.println("user.getRole(): " + user.getRole());
        System.out.println("user.getRole().getName(): " + user.getRole().getName());
        System.out.println("Comparando con: " + RoleEnum.OWNER.getValue());

        return RoleEnum.OWNER.getValue().equals(user.getRole().getName());
    }
}
