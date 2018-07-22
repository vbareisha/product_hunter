package com.bareisha.producthunter.core.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private String title;
    private BigDecimal price;
    private BigDecimal priceDiscount;
    private String country;

}
