package com.kitchenapp.kitchentech.business.service;

import com.kitchenapp.kitchentech.business.model.Account;

import java.util.List;

public interface AccountService {
    public abstract List<Account> getAllAccounts(Long restaurantId);
    public abstract Account getAccountById(Long id);
    public abstract Account createAccount(Account account);
    public abstract Account updateAccount(Account account);
    public abstract void deleteAccount(Long id);
}
