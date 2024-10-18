package com.kitchenapp.kitchentech.business.repository;

import com.kitchenapp.kitchentech.business.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    boolean existsById(Long id);
    List<Restaurant> findAll();
}
