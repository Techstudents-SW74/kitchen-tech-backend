package com.kitchenapp.kitchentech.business.service.iml;


import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.model.Restaurant;
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
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    @Override
    public Product updateProduct(Product product){
        Product productToUpdate = productRepository.findById(product.getId()).orElse(null);
        if (productToUpdate != null){
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setProductImageUrl(product.getProductImageUrl());
            productToUpdate.setProductPrice(product.getProductPrice());
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

    @Override
    public void validateProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (product.getProductName() == null || product.getProductName().isEmpty()){
            throw new IllegalArgumentException("El nombre del producto debe ser obligatorio");
        }
        if (product.getProductName().length() > 50 ){
            throw new IllegalArgumentException("El nombre del producto no debe exceder los 50 caracteres");
        }
        if (product.getProductPrice() == null || product.getProductPrice() <= 0){
            throw new IllegalArgumentException("El precio del producto es obligatorio");
        }
        if (product.getCategory() == null || product.getCategory().isEmpty()){
            throw new IllegalArgumentException("La categoria del producto es obligatoria");
        }
        if (product.getCategory().length() > 50){
            throw new IllegalArgumentException("La categoria del producto no debe exceder los 50 caracteres");
        }
        if (product.getProductPrice() == null){
            throw new IllegalArgumentException("El precio del producto es obligatoria");
        }
    }
}
