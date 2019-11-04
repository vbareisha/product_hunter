package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.service.api.IParserHtml;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(
        basePackages = "com.bareisha.producthunter.web.controller",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = {HealthStatusController.class, ProductController.class}))
public class ParserControllerTestConfiguration {

    @MockBean
    private IParserHtml parserHtml;
}
