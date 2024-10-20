package com.kitchenapp.kitchentech.iot.model;

import com.kitchenapp.kitchentech.business.model.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_restaurant")
public class TableRestaurant {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false, length = 50)
    private Long tableNumber;

    @Column(name = "table_capacity", nullable = false, length = 10)
    private Long tableCapacity;

    @Column(name = "table_status", length = 250)
    private String tableStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = true)
    private Restaurant restaurant;
}
