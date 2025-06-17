package com.plazoleta.users.domain.spi;

import com.plazoleta.users.domain.model.Role;

public interface IRolePersistencePort {
    Role findByName(String name);
}
