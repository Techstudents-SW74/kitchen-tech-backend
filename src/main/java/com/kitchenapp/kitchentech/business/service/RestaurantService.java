package com.kitchenapp.kitchentech.business.service;

import com.kitchenapp.kitchentech.authentication.model.RegisterRequest;
import com.kitchenapp.kitchentech.business.model.Restaurant;
import com.kitchenapp.kitchentech.business.model.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public abstract Restaurant createRestaurant(Restaurant restaurant);
    public abstract Restaurant getRestaurantById(Long id);
    public abstract Restaurant updateRestaurant(Restaurant restaurant);
    public abstract void deleteRestaurant(Long id);
    public abstract List<Restaurant> getAllRestaurants();
    public void existsRestaurantById(Long id);
    public void validateRestaurant(Restaurant restaurant);
    public void existsRestaurantByName(RestaurantRequest restaurantRequest);
}
