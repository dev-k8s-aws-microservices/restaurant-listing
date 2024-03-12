package com.code.decode.restaurantlisting.mapper;

import com.code.decode.restaurantlisting.dto.RestaurantDTO;
import com.code.decode.restaurantlisting.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public interface RestaurantMapping {

    static RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant) {

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setRestaurantDescription(restaurant.getRestaurantDescription());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setCity(restaurant.getCity());
        return  restaurantDTO;
    }

    static Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO) {

        Restaurant restaurant = new Restaurant();

        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setRestaurantDescription(restaurantDTO.getRestaurantDescription());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setCity(restaurantDTO.getCity());

        return  restaurant;
    }

}
