package com.kitchenapp.kitchentech.business.service.iml;


import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.repository.ProductRepository;
import com.kitchenapp.kitchentech.business.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @Override
    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }
    @Override
    public void delete(Long id){
        productRepository.deleteById(id);
    }
    @Override
    public Product update(Product product){
        Product productToUpdate = productRepository.findById(product.getId()).orElse(null);
        if (productToUpdate != null){
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setCurrency(product.getCurrency());
            productToUpdate.setProductImageUrl(product.getProductImageUrl());
            productToUpdate.setProductPrice(product.getProductPrice());
            productToUpdate.setTax(product.getTax());
            return productRepository.save(productToUpdate);
        }
        else {
            return null;
        }
    }
    @Override
    public List<Product> getProductByCategory(String category){
        if (category != null){
            return productRepository.findByCategory(category);
        }
        else {
            return null;
        }
    }
}
