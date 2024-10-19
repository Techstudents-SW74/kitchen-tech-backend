package com.kitchenapp.kitchentech.business.repository;

import com.kitchenapp.kitchentech.business.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
}
