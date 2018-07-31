package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.service.api.IParserHtml;
import com.bareisha.producthunter.service.api.IProductService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.bareisha.producthunter.web.controller")
public class HealthStatusTestConfiguration {
    @MockBean
    private IParserHtml parserHtml;

    @MockBean
    private IProductService<ProductDto> productService;
}
