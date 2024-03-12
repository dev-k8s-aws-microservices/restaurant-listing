package com.code.decode.restaurantlisting.service;

import com.code.decode.restaurantlisting.dto.RestaurantDTO;
import com.code.decode.restaurantlisting.entity.Restaurant;
import com.code.decode.restaurantlisting.mapper.RestaurantMapper;
import com.code.decode.restaurantlisting.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @InjectMocks
    RestaurantImpl restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants() {
        // Create mock restaurants
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), 1);
        for (int i = 0; i < mockRestaurants.size(); i++) {
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB() {
        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);

        // Mock the repository behavior
        when(restaurantRepository.save(mockRestaurant)).thenReturn(mockRestaurant);

        // Call the service method
        RestaurantDTO savedRestaurantDTO = restaurantService.addRestaurantInDB(mockRestaurantDTO);

        // Verify the result
        assertEquals(mockRestaurantDTO, savedRestaurantDTO);

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).save(mockRestaurant);
    }

    @Test
    public void testFetchRestaurantById_ExistingId() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the repository
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // Call the service method
        RestaurantDTO response = restaurantService.findById(mockRestaurantId);

        // Verify the response
        assertEquals(mockRestaurantId, 1);

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testFetchRestaurantById_NonExistingId() {
        // Create a mock non-existing restaurant ID
        Integer mockRestaurantId = 1;

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the service method
        RestaurantDTO response = restaurantService.findById(mockRestaurantId);

        // Verify the response
        assertEquals(null, response);

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }


}
