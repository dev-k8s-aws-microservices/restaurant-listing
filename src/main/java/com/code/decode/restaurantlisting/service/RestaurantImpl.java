package com.code.decode.restaurantlisting.service;

import com.code.decode.restaurantlisting.dto.RestaurantDTO;
import com.code.decode.restaurantlisting.entity.Restaurant;
import com.code.decode.restaurantlisting.mapper.RestaurantMapping;
import com.code.decode.restaurantlisting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantDTO> findAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map(RestaurantMapping::mapRestaurantToRestaurantDTO).collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {

        Restaurant savedRestaurant = restaurantRepository.save(RestaurantMapping.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapping.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    @Override
    public RestaurantDTO findById(int id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent())
            return RestaurantMapping.mapRestaurantToRestaurantDTO(restaurant.get());
        return null;
    }
}
