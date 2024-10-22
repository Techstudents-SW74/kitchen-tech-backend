package com.kitchenapp.kitchentech.business.service.iml;

import com.kitchenapp.kitchentech.business.model.Account;
import com.kitchenapp.kitchentech.business.repository.AccountRepository;
import com.kitchenapp.kitchentech.business.service.AccountService;
import com.kitchenapp.kitchentech.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts(Long restaurantId) {
        return accountRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {

        Account accountToUpdate = getAccountById(account.getId());
        if(accountToUpdate != null){
            accountToUpdate.setClient(account.getClient());
            accountToUpdate.setUser(account.getUser());
            accountToUpdate.setOrder(account.getOrder());
            accountToUpdate.setRestaurantId(account.getRestaurantId());
            accountToUpdate.setState(account.getState());
            accountToUpdate.setTotalGuests(account.getTotalGuests());
            accountToUpdate.setTotalAccount(account.getTotalAccount());
            accountToUpdate.setDateCreated(account.getDateCreated());
            accountToUpdate.setDateLog(account.getDateLog());
            return accountRepository.save(accountToUpdate);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void validateAccount(Account account) {
        if(account == null){
            throw new ValidationException("La cuenta no puede ser nula");
        }
        if(account.getOrder() == null){
            throw new ValidationException("La orden de la cuenta no puede estar vacía");
        }
    }
}
