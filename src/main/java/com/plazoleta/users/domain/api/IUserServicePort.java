package com.plazoleta.users.domain.api;

public interface IUserServicePort {
    boolean isUserOwner(Long userId);
}
