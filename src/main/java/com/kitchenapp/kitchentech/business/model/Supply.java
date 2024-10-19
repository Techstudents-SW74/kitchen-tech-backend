package com.kitchenapp.kitchentech.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supply")
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supply_name", nullable = false, length = 50)
    private String supplyName;

    @Column(name = "supply_category", nullable = false, length = 50)
    private String supplyCategory;

    @Column (name = "currently_in_stock", nullable = false)
    private int currentlyInStock;

    @Column (name = "metric_unit", nullable = false, length = 10)
    private String metricUnit;

    @Column (name = "cost_per_unit", nullable = false)
    private double costPerUnit;

    @Column (name = "state_of_supply", nullable = false, length = 50)
    private String stateOfSupply;

    @Column (name = "estimated_daily_use", nullable = false)
    private double estimatedDailyUse;
}
