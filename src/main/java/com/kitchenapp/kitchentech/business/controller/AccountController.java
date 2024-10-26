package com.kitchenapp.kitchentech.business.controller;

import com.kitchenapp.kitchentech.business.model.Account;
import com.kitchenapp.kitchentech.business.model.AccountProduct;
import com.kitchenapp.kitchentech.business.service.AccountProductService;
import com.kitchenapp.kitchentech.business.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kitchentech/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountProductService accountProductService;

    public AccountController(AccountService accountService, AccountProductService accountProductService) {
        this.accountService = accountService;
        this.accountProductService = accountProductService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/restaurant/{restaurantId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Account>> getAllAccounts(@PathVariable(name="restaurantId")Long restaurantId){
        List<Account> accounts = accountService.getAllAccounts(restaurantId);
        if(accounts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable(name = "accountId") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account
    // Method: POST
    @Transactional
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        account.updateTotalAccount();  // Calcula el total antes de guardar la cuenta
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: PUT
    @Transactional
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "accountId") Long accountId, @RequestBody Account account) {
        if (accountService.getAccountById(accountId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza el total basado en la lista de AccountProduct del request
        account.updateTotalAccount();
        Account updatedAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: DELETE
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "accountId") Long accountId) {
        if (accountService.getAccountById(accountId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: POST
    @PostMapping("/{accountId}/products")
    public ResponseEntity<AccountProduct> addProductToAccount(@PathVariable(name = "accountId") Long accountId,
                                                              @RequestBody AccountProduct accountProduct) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountProduct.setAccount(account); // Asociar el producto a la cuenta
        accountProductService.addAccountProduct(accountProduct); // Llamar al servicio
        return new ResponseEntity<>(accountProduct, HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/account/{accountId}
    // Method: PUT
    @PutMapping("/{accountId}/products/{productId}")
    public ResponseEntity<AccountProduct> updateProductInAccount(@PathVariable(name = "accountId") Long accountId,
                                                                 @PathVariable(name = "productId") Long productId,
                                                                 @RequestBody AccountProduct accountProduct) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccountProduct updatedProduct = accountProductService.updateAccountProduct(accountId, productId, accountProduct);
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
