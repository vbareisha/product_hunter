package com.bareisha.producthunter.web.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(
        basePackages = "com.bareisha.producthunter.web.controller",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = {ParserController.class, ProductController.class}))
public class HealthStatusTestConfiguration {
}
