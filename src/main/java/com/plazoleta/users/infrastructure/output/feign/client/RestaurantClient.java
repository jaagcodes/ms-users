package com.plazoleta.users.infrastructure.output.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "restaurantClient", url = "${restaurants.service.url}")
public interface RestaurantClient {
    @GetMapping("/restaurants/{restaurantId}/validate-owner")
    Boolean isRestaurantOwner(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestParam("userId") Long userId
    );
}
