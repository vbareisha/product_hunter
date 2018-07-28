package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import com.bareisha.producthunter.repository.base.IProductRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.bareisha.producthunter.service",
                                "com.bareisha.producthunter.core.converter"})
public class EDostavkaParserTestConfiguration {

    @MockBean
    private IProductRepository productRepository;

    @MockBean(name = "productDtoProductIConverter")
    private IConverter<ProductDto, Product> productDtoProductIConverter;

    @MockBean(name = "productProductDtoIConverter")
    private IConverter<Product, ProductDto> productProductDtoIConverter;

}
