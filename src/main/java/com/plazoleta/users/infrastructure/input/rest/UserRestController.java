package com.plazoleta.users.infrastructure.input.rest;

import com.plazoleta.users.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @GetMapping("/{id}/validate-owner")
    public ResponseEntity<Boolean> validateOwner(@PathVariable Long id) {
        boolean isOwner = userHandler.isUserOwner(id);
        return ResponseEntity.ok(isOwner);
    }
}
