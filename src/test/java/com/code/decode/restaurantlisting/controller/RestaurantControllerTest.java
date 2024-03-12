package com.code.decode.restaurantlisting.controller;

import com.code.decode.restaurantlisting.dto.RestaurantDTO;
import com.code.decode.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Mock
    RestaurantService restaurantService;

    @Test
    public void testFetchAllRestaurants() {

        // Mock the Service Behaviour

        List<RestaurantDTO> mockRestaurants = Arrays.asList(
                new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Description"),
                new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Description"),
                new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Description")
        );

        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);

        // Call the Controller Method.

        ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurants();

        // Verify the Response

       Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

       Assertions.assertEquals(mockRestaurants, response.getBody());

       // Verify that the Service method was called

        verify(restaurantService, times(1)).findAllRestaurants();


    }

    @Test
    public void testSaveRestaurants() {

        // Mock the Service Behaviour

        RestaurantDTO mockRestaurants = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Description");


        when(restaurantService.addRestaurantInDB(mockRestaurants)).thenReturn(mockRestaurants);

        // Call the Controller Method.

        ResponseEntity<RestaurantDTO> response =restaurantController.saveRestaurant(mockRestaurants);

        // Verify the Response

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assertions.assertEquals(mockRestaurants, response.getBody());

        // Verify that the Service method was called

        verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurants);


    }
}
