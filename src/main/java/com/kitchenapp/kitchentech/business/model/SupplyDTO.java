package com.kitchenapp.kitchentech.business.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SupplyDTO {
    private String supplyName;
    private String metricUnit;
    private double quantity;
}