package com.kitchenapp.kitchentech.business.controller;

import com.kitchenapp.kitchentech.business.model.Restaurant;
import com.kitchenapp.kitchentech.business.model.RestaurantRequest;
import com.kitchenapp.kitchentech.business.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kitchentech/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/restaurant
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/restaurant/{restaurantId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(name="restaurantId") Long restaurantId){
        restaurantService.existsRestaurantById(restaurantId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        return new ResponseEntity<Restaurant>(restaurant,HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/restaurant/{restaurantId}
    // Method: PUT
    @Transactional
    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable(name="restaurantId")Long restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.existsRestaurantById(restaurantId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        restaurantService.validateRestaurant(restaurantRequest);

        Restaurant restaurantSaved = restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity<Restaurant>(restaurantSaved,HttpStatus.OK);
    }
}
