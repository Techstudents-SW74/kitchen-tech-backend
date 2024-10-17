package com.kitchenapp.kitchentech.business.controller;

import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kitchentech/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Find all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        if (productService.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    // Find product by id
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        if(productService.findById(productId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    // Update a product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        if(productService.findById(productId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    // Delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        if(productService.findById(productId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    // Find product by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        if (productService.getProductByCategory(category).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productService.getProductByCategory(category), HttpStatus.OK);
    }
}
