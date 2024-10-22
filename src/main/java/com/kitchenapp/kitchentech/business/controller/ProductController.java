package com.kitchenapp.kitchentech.business.controller;

import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    // URL: http://localhost:8080/api/kitchentech/v1/product/restaurant/{restaurantId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable(name = "restaurantId") Long restaurantId) {
        if (productService.getAllProducts(restaurantId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productService.getAllProducts(restaurantId), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/product/{productId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "productId") Long productId) {
        if(productService.getProductById(productId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/product
    // Method: POST
    @Transactional
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.validateProduct(product);
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/product/{productId}
    // Method: PUT
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "productId") Long productId, @RequestBody Product product) {
        if(productService.getProductById(productId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.validateProduct(product);
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/product/{productId}
    // Method: DELETE
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") Long productId) {
        if(productService.getProductById(productId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

}
