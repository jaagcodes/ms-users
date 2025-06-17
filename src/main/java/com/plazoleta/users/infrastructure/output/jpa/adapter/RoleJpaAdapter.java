package com.plazoleta.users.infrastructure.output.jpa.adapter;

import com.plazoleta.users.domain.model.Role;
import com.plazoleta.users.domain.spi.IRolePersistencePort;
import com.plazoleta.users.infrastructure.output.jpa.entity.RoleEntity;
import com.plazoleta.users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.plazoleta.users.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;


    @Override
    public Role findByName(String name) {
        RoleEntity entity = roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
        return roleEntityMapper.toDomain(entity);
    }
}
