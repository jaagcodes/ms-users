package com.plazoleta.users.application.handler;

import com.plazoleta.users.application.dto.CreateClientRequest;
import com.plazoleta.users.application.dto.CreateEmployeeRequest;
import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.mapper.ClientRequestMapper;
import com.plazoleta.users.application.mapper.EmployeeRequestMapper;
import com.plazoleta.users.application.mapper.OwnerRequestMapper;
import com.plazoleta.users.domain.api.IOwnerServicePort;
import com.plazoleta.users.domain.api.IUserServicePort;
import com.plazoleta.users.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IOwnerServicePort ownerServicePort;
    private final OwnerRequestMapper ownerRequestMapper;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final ClientRequestMapper clientRequestMapper;

    @Override
    public boolean isUserOwner(Long userId) {
        return userServicePort.isUserOwner(userId);
    }
    @Override
    public void createOwner(CreateOwnerRequest request) {
        User user = ownerRequestMapper.toUser(request);
        ownerServicePort.createOwner(user);
    }

    @Override
    public void createEmployee(CreateEmployeeRequest request) {
        User user = employeeRequestMapper.toUser(request);
        userServicePort.createEmployee(user, request.getRestaurantId());
    }

    @Override
    public User createClient(CreateClientRequest request) {
        User user = clientRequestMapper.toUser(request);
        return userServicePort.createClient(user);
    }

    @Override
    public boolean isEmployeeOfRestaurant(Long employeeId, Long restaurantId) {
        return userServicePort.isEmployeeOfRestaurant(employeeId, restaurantId);
    }


}
