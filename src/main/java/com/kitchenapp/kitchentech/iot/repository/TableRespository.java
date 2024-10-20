package com.kitchenapp.kitchentech.iot.repository;

import com.kitchenapp.kitchentech.iot.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRespository extends JpaRepository<TableRestaurant, Long> {
}
