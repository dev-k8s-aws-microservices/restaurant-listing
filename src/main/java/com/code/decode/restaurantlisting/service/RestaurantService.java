package com.code.decode.restaurantlisting.service;

import com.code.decode.restaurantlisting.dto.RestaurantDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> findAllRestaurants();

    RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO);

    RestaurantDTO findById(int id);
}
