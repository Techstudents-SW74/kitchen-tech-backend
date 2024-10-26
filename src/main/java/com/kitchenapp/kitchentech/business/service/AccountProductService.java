package com.kitchenapp.kitchentech.business.service;

import com.kitchenapp.kitchentech.business.model.AccountProduct;

import java.util.List;

public interface AccountProductService {
    void addAccountProduct(AccountProduct accountProduct);
    AccountProduct getAccountProductById(Long id);
    AccountProduct updateAccountProduct(Long accountId, Long productId, AccountProduct updatedAccountProduct);
}
