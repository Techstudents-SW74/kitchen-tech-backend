package com.kitchenapp.kitchentech.business.repository;

import com.kitchenapp.kitchentech.business.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
