package com.plazoleta.users.application.handler;

import com.plazoleta.users.application.handler.IAuthHandler;
import com.plazoleta.users.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHandler implements IAuthHandler {

    private final IAuthServicePort authServicePort;

    @Override
    public String login(String email, String password) {
        return authServicePort.login(email, password);
    }
}
