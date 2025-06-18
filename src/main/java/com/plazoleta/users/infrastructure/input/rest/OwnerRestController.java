package com.plazoleta.users.infrastructure.input.rest;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.handler.IOwnerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class OwnerRestController {

    private final IOwnerHandler ownerHandler;

    @Operation(summary = "Create a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> createOwner(@RequestBody CreateOwnerRequest request) {
        ownerHandler.createOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
