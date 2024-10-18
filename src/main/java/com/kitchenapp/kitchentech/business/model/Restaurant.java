package com.kitchenapp.kitchentech.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 150)
    private String name;

    @Column(name="email", nullable = false, length = 150)
    private String email;

    @Column(name="description", nullable = false, length = 300)
    private String description;

    @Column(name="image")
    private String image;

    @Column(name="logo", nullable = true)
    private String logo;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "district", length = 100)
    private String district;

}
