package com.plazoleta.users.infrastructure.security.adapter;

import com.plazoleta.users.domain.api.ISecurityServicePort;
import com.plazoleta.users.infrastructure.security.util.SecurityDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceAdapter implements ISecurityServicePort {

    @Override
    public Long getCurrentUserId() {
        return SecurityDetails.getCurrentUser().id(); // Obtenemos el ID del token JWT
    }
}
