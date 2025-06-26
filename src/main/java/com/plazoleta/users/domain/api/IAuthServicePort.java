package com.plazoleta.users.domain.api;

public interface IAuthServicePort {
    String login(String email, String password);
}
