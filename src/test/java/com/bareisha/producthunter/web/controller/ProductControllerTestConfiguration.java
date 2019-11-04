package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.service.api.IProductService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(
        basePackages = "com.bareisha.producthunter.web.controller",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = {HealthStatusController.class, ParserController.class}))
public class ProductControllerTestConfiguration {

    @MockBean
    private IProductService<ProductDto> productService;
}
