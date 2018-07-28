package com.bareisha.producthunter.core.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDto {

    private String title;
    private Double price;
    private Double priceDiscount;
    private String country;
    private UUID uuid;
    private Long id;
    private LocalDateTime dtUpdate;
}
