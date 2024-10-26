package com.kitchenapp.kitchentech.business.service.iml;

import com.kitchenapp.kitchentech.business.model.Account;
import com.kitchenapp.kitchentech.business.model.AccountProduct;
import com.kitchenapp.kitchentech.business.repository.AccountProductRepository;
import com.kitchenapp.kitchentech.business.repository.AccountRepository;
import com.kitchenapp.kitchentech.business.service.AccountProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountProductServiceImpl implements AccountProductService {
    private final AccountProductRepository accountProductRepository;
    private final AccountRepository accountRepository;

    public AccountProductServiceImpl(AccountRepository accountRepository, AccountProductRepository accountProductRepository) {
        this.accountRepository = accountRepository;
        this.accountProductRepository = accountProductRepository;
    }

    @Transactional
    public void addAccountProduct(AccountProduct accountProduct) {
        accountProductRepository.save(accountProduct);
    }

    @Transactional
    public AccountProduct updateAccountProduct(Long accountId, Long productId, AccountProduct updatedAccountProduct) {
        // Verificar que la cuenta existe
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return null; // O lanzar una excepción
        }

        // Buscar el producto en la cuenta
        AccountProduct existingProduct = accountProductRepository.findById(productId).orElse(null);
        if (existingProduct == null || !existingProduct.getAccount().getId().equals(accountId)) {
            return null; // O lanzar una excepción
        }

        // Actualizar los detalles del producto
        existingProduct.setProductId(updatedAccountProduct.getProductId());
        existingProduct.setProductName(updatedAccountProduct.getProductName());
        existingProduct.setPrice(updatedAccountProduct.getPrice());
        existingProduct.setQuantity(updatedAccountProduct.getQuantity());

        return accountProductRepository.save(existingProduct);
    }
}
