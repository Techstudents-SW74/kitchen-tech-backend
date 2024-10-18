package com.kitchenapp.kitchentech.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequest {

    private String name;
    private String email;
    private String description;
    private String image;
    private String logo;
    private String city;
    private String district;
}
