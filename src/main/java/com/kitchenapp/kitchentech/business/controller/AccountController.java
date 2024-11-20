package com.kitchenapp.kitchentech.business.controller;

import com.kitchenapp.kitchentech.business.model.Account;
import com.kitchenapp.kitchentech.business.service.AccountService;
import com.kitchenapp.kitchentech.business.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kitchentech/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final OrderService orderService;

    public AccountController(AccountService accountService, OrderService orderService){
        this.accountService = accountService;
        this.orderService = orderService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/restaurant/{restaurantId}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Account>> getAllAccounts(@PathVariable(name="restaurantId")Long restaurantId){
        if(accountService.getAllAccounts(restaurantId).isEmpty()){
            return new ResponseEntity<List<Account>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(restaurantId),HttpStatus.OK);
    }



    // URL: http://localhost:8080/api/kitchentech/v1/account
    // Method: POST

    @Transactional
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        orderService.createOrder(account.getOrder());
        accountService.validateAccount(account);
        return new ResponseEntity<Account>(accountService.createAccount(account),HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: PUT
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name="accountId")Long accountId,@RequestBody Account account){
        if(accountService.getAccountById(accountId)==null){
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        orderService.updateOrder(account.getOrder());
        accountService.validateAccount(account);
        return new ResponseEntity<Account>(accountService.updateAccount(account),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: DELETE
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name="accountId")Long accountId){

        Account account = accountService.getAccountById(accountId);

        if(account == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        orderService.deleteOrder(account.getOrder().getId());
        accountService.deleteAccount(accountId);

        return new ResponseEntity<String>("Account deleted successfully",HttpStatus.OK);
    }
}
