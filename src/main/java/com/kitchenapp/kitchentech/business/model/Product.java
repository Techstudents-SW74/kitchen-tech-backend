package com.kitchenapp.kitchentech.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price", nullable = false, length = 10)
    private Double productPrice;

    @Column(name = "product_image_url", nullable = false, length = 250)
    private String productImageUrl;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "currency", nullable = false, length = 5)
    private String currency;

    @Column(name = "tax", nullable = false)
    private Double tax;

    //@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Supply> supplies;
}
