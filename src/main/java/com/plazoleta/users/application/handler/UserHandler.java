package com.plazoleta.users.application.handler;

import com.plazoleta.users.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;

    @Override
    public boolean isUserOwner(Long userId) {
        return userServicePort.isUserOwner(userId);
    }
}
