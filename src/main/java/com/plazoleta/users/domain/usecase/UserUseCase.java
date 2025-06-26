package com.plazoleta.users.domain.usecase;

import com.plazoleta.users.domain.api.ISecurityServicePort;
import com.plazoleta.users.domain.api.IUserServicePort;
import com.plazoleta.users.domain.model.User;
import com.plazoleta.users.domain.spi.IRestaurantClientPort;
import com.plazoleta.users.domain.spi.IRolePersistencePort;
import com.plazoleta.users.domain.spi.IUserPersistencePort;
import com.plazoleta.users.domain.utils.RoleEnum;
import com.plazoleta.users.infrastructure.exception.UserNotFoundException;
import com.plazoleta.users.infrastructure.exception.UserRoleNotAssignedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserUseCase implements IUserServicePort {

    private static final Logger logger = LoggerFactory.getLogger(UserUseCase.class);
    private final IUserPersistencePort userPersistencePort;
    private final IRestaurantClientPort restaurantClientPort;
    private final IRolePersistencePort rolePersistencePort;
    private final ISecurityServicePort securityServicePort;

    public UserUseCase(
            IUserPersistencePort userPersistencePort,
            IRestaurantClientPort restaurantClientPort,
            IRolePersistencePort rolePersistencePort,
            ISecurityServicePort securityServicePort
    ) {
        this.userPersistencePort = userPersistencePort;
        this.restaurantClientPort = restaurantClientPort;
        this.rolePersistencePort = rolePersistencePort;
        this.securityServicePort = securityServicePort;
    }

    public boolean isUserOwner(Long userId) {
        User user = userPersistencePort.findById(userId);
        logger.info("isUserOwner: user id {} ", user);
        if (user == null) {
            throw new UserNotFoundException();
        }

        if (user.getRole() == null) {
            throw new UserRoleNotAssignedException();
        }

        logger.info("user role {} ", user.getRole().getId());
        logger.info("user role name {} ", user.getRole().getName());
        logger.info("Comparing con: {}", RoleEnum.OWNER.getValue());

        return RoleEnum.OWNER.getValue().equals(user.getRole().getName());
    }

    public User createEmployee(User user, Long restaurantId) {
        logger.info("Employee creation {} ", user);
        Long currentUserId = securityServicePort.getCurrentUserId();

        logger.info("Owner who creates employee {} ", currentUserId);

        // 2. Verificar que el OWNER sea dueño del restaurante
        boolean isOwnerOfRestaurant = restaurantClientPort.isRestaurantOwner(restaurantId, currentUserId );
        if (!isOwnerOfRestaurant) {
            throw new UserRoleNotAssignedException();
        }

        // 3. Asignar rol EMPLEADO
        user.setRole(rolePersistencePort.findByName(RoleEnum.EMPLOYEE.getValue()));

        // 4. Guardar usuario en la BD
        return userPersistencePort.saveUser(user);
    }
}
