package com.kitchenapp.kitchentech.business.service;

import com.kitchenapp.kitchentech.business.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Product update(Product product);
    List<Product> getProductByCategory(String category);

}
