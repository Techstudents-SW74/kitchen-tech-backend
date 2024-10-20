package com.kitchenapp.kitchentech.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created", nullable = false)
    private LocalDate date_created;

    @Column(name="state",nullable = false)
    private String state;

    // Relación Many-to-Many con Product
    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @Column(name = "total_price",nullable = false)
    private Float totalPrice;

    // Método para calcular y actualizar el total
    private void updateTotalPrice() {
        this.totalPrice = (float) products.stream()
                .mapToDouble(Product::getProductPrice)
                .sum();
    }
}
