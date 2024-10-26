package com.kitchenapp.kitchentech.business.repository;

import com.kitchenapp.kitchentech.business.model.AccountProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountProductRepository extends JpaRepository<AccountProduct, Long> {
    List<AccountProduct> findByAccountId(Long accountId);
}