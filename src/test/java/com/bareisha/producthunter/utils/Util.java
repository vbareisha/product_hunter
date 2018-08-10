package com.bareisha.producthunter.utils;

import com.bareisha.producthunter.core.dto.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Util {

    // Get products for test
    public static List<ProductDto> getProductDtos() {
        ProductDto product = new ProductDto();
        product.setTitle("Баклажан 1 кг., фасовка 0.6 - 0.8 кг");
        product.setCountry("Украина");
        product.setPrice((new BigDecimal(2.99)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
        product.setPriceDiscount((new BigDecimal(2.29)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
        product.setUuid(UUID.randomUUID());
        product.setDtUpdate(LocalDateTime.now());
        product.setImage("./Баклажан - Каталог товаров_files/144753_190x190@2x.png.jpg");

        List<ProductDto> expectedProductList = new ArrayList<>();
        expectedProductList.add(product);
        product = new ProductDto();
        product.setTitle("Баклажан 1 кг., фасовка 0.6 - 0.7 кг");
        product.setCountry("Испания");
        product.setPrice((new BigDecimal(4.49)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
        product.setPriceDiscount(null);
        product.setUuid(UUID.randomUUID());
        product.setDtUpdate(LocalDateTime.now());
        product.setImage("./Баклажан - Каталог товаров_files/269518_190x190@2x.png.jpg");
        expectedProductList.add(product);
        return expectedProductList;
    }

}
