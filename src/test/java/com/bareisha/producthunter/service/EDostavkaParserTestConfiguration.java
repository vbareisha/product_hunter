package com.bareisha.producthunter.service;

import com.bareisha.producthunter.repository.base.IProductRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.bareisha.producthunter.service")
public class EDostavkaParserTestConfiguration {

    @MockBean
    private IProductRepository productRepository;
}
