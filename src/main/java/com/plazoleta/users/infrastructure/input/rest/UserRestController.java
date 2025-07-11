package com.plazoleta.users.infrastructure.input.rest;

import com.plazoleta.users.application.dto.CreateClientRequest;
import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.handler.IUserHandler;
import com.plazoleta.users.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    private final IUserHandler userHandler;

    @GetMapping("/{id}/validate-owner")
    public ResponseEntity<Boolean> validateOwner(@PathVariable Long id) {
        boolean isOwner = userHandler.isUserOwner(id);
        return ResponseEntity.ok(isOwner);
    }

    @Operation(summary = "Create a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/owner")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createOwner(@RequestBody CreateOwnerRequest request) {
        userHandler.createOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/employee")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        userHandler.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/client")
    public ResponseEntity<Map<String, Object>> createClient(@Valid @RequestBody CreateClientRequest request) {
        User client = userHandler.createClient(request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "client created successfully");
        response.put("client", client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/validate-employee")
    public ResponseEntity<Boolean> validateEmployeeOfRestaurant(
            @RequestParam Long employeeId,
            @RequestParam Long restaurantId
    ) {
        boolean isEmployee = userHandler.isEmployeeOfRestaurant(employeeId, restaurantId);
        return ResponseEntity.ok(isEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userHandler.findUserById(id);
        logger.info("[UserRestController] user= {}", user);
        return ResponseEntity.ok(user);
    }

}
