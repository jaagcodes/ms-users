package com.plazoleta.users.domain.api;

import com.plazoleta.users.domain.model.User;

public interface IOwnerServicePort {
    User createOwner(User user);
}
