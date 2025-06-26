package com.plazoleta.users.domain.usecase;

import com.plazoleta.users.domain.api.IAuthServicePort;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.infrastructure.exception.BadCredentialsException;
import com.plazoleta.users.infrastructure.security.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthUseCase implements IAuthServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthUseCase(IUserPersistencePort userPersistencePort,
                       JwtUtil jwtUtil,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String email, String password) {
        User user = userPersistencePort.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException());

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException();
        }

        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().getName());
    }
}
