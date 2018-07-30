package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.converter.ProcuctDtoToProductConverter;
import com.bareisha.producthunter.core.converter.ProductToProductDtoConverter;
import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import com.bareisha.producthunter.repository.base.IProductRepository;
import com.bareisha.producthunter.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.bareisha.producthunter.service",
                                "com.bareisha.producthunter.core.converter"})
public class EDostavkaParserTestConfiguration {

    @Autowired
    private IProductService<ProductDto> productService;

    @Bean("productDtoProductIConverter")
    public IConverter<ProductDto, Product> productDtoProductIConverter() {
        return new ProcuctDtoToProductConverter<>();
    }

    @Bean("productProductDtoIConverter")
    public IConverter<Product, ProductDto> productProductDtoIConverter() {
        return new ProductToProductDtoConverter<>();
    }

}
