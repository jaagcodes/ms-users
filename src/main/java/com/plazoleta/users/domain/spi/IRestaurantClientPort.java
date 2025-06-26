package com.plazoleta.users.domain.spi;

public interface IRestaurantClientPort {
    boolean isRestaurantOwner(Long ownerId, Long restaurantId);
}
