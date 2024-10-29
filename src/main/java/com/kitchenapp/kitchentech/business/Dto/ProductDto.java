package com.kitchenapp.kitchentech.business.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productName;
    private Double productPrice;
    private String productImageUrl;
    private String category;
    private Long restaurantId;
}