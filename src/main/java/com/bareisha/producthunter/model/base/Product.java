package com.bareisha.producthunter.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", indexes = {
        @Index(name = "products_pkey", unique = true, columnList = "id"),
        @Index(name = "product_uuid_idx", unique = true, columnList = "uuid")
})
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "country")
    private String country;

    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "price_discount")
    private BigDecimal priceDiscount;

    @Column(name = "img")
    private String image;
}
