package com.kitchenapp.kitchentech.business.Dto;

import lombok.Data;

@Data
public class AccountProductDto {
    private Long id;
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Long accountId;
}
