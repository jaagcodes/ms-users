package com.plazoleta.users.domain.api;

import com.plazoleta.users.domain.model.User;

public interface IUserServicePort {
    boolean isUserOwner(Long userId);
    User createEmployee(User user, Long restaurantId);
    User createClient(User user);
}
