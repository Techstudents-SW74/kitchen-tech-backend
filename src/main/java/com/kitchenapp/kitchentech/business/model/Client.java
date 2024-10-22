package com.kitchenapp.kitchentech.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "name",  nullable = false, length = 150)
    private String name;

    @Column(name = "type_document", nullable = true)
    private String type_document;

    @Column(name="email", nullable = true, length = 200)
    private String email;

    @Column(name="phone", nullable = true, length = 20)
    private String phone;

    @Column(name="birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(name = "restaurant_id", nullable = false)
    private long restaurantId;
}
