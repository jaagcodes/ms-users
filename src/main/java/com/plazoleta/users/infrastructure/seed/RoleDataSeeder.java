package com.plazoleta.users.infrastructure.seed;

import com.plazoleta.users.domain.utils.RoleEnum;
import com.plazoleta.users.infrastructure.output.jpa.entity.RoleEntity;
import com.plazoleta.users.infrastructure.output.jpa.repository.IRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDataSeeder {

    private final IRoleRepository roleRepository;

    @PostConstruct
    public void seedRoles() {
        if (!roleRepository.findByName("OWNER").isPresent()) {
            RoleEntity owner = new RoleEntity();
            owner.setName(RoleEnum.OWNER.getValue());
            owner.setDescription("Propietario de restaurante");
            roleRepository.save(owner);
        }

        if (!roleRepository.findByName("ADMIN").isPresent()) {
            RoleEntity admin = new RoleEntity();
            admin.setName(RoleEnum.ADMIN.getValue());
            admin.setDescription("Administrador del sistema");
            roleRepository.save(admin);
        }

        System.out.println("✅ Roles semilla insertados");
    }
}
