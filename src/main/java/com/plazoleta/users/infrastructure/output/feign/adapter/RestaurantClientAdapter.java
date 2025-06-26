package com.plazoleta.users.infrastructure.output.feign.adapter;

import com.plazoleta.users.domain.spi.IRestaurantClientPort;
import com.plazoleta.users.infrastructure.exception.ApplicationException;
import com.plazoleta.users.infrastructure.exception.ForbiddenException;
import com.plazoleta.users.infrastructure.exception.RestaurantNotFoundException;
import com.plazoleta.users.infrastructure.output.feign.client.RestaurantClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantClientAdapter implements IRestaurantClientPort {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantClientAdapter.class);
    private final RestaurantClient restaurantClient;

    @Override
    public boolean isRestaurantOwner(Long restaurantId, Long ownerId) {
        logger.info("Validating if restaurant with ID {} has this owner", ownerId);
        try{
            boolean isOwner = restaurantClient.isRestaurantOwner(restaurantId, ownerId);
            logger.info("User {} isOwner: {} of restaurant with id: {}", ownerId, isOwner, restaurantId);
            return isOwner;
        } catch (FeignException.NotFound e){
            logger.warn("Restaurant with ID {} not found in Restaurant Service", restaurantId);
            throw new RestaurantNotFoundException();
        } catch (FeignException.Forbidden e) {
            logger.warn("User with ID {} does not have permission", ownerId);
            throw new ForbiddenException();
        } catch (FeignException e) {
            logger.error("Unexpected error calling User Service: status={} message={}", e.status(), e.getMessage());
            throw new ApplicationException("Unexpected error consuming users service: "+ e.status(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
