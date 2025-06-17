package com.plazoleta.users.application.handler;

import com.plazoleta.users.application.dto.CreateOwnerRequest;
import com.plazoleta.users.application.mapper.OwnerRequestMapper;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.usecase.OwnerUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler{

    private final OwnerUseCase ownerUseCase;
    private final OwnerRequestMapper ownerRequestMapper;
    @Override
    public void createOwner(CreateOwnerRequest request) {
        User user = ownerRequestMapper.toUser(request);
        ownerUseCase.createOwner(user);
    }
}
