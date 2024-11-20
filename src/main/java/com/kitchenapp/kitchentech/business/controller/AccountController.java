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



    
}
