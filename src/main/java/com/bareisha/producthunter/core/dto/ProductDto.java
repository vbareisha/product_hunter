package com.bareisha.producthunter.core.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String title;
    private Double price;
    private Double priceDiscount;
    private String country;

}
