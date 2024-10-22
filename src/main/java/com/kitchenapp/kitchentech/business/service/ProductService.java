package com.kitchenapp.kitchentech.business.service;

import com.kitchenapp.kitchentech.business.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts(Long restaurantId);
    public Product getProductById(Long id);
    public Product createProduct(Product product);
    public void deleteProduct(Long id);
    public Product updateProduct(Product product);
    public List<Product> getProductByCategory(String category);
    public void validateProduct(Product product);


}
