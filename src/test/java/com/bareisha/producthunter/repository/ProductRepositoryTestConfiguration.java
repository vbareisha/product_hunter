package com.bareisha.producthunter.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = "com.bareisha.producthunter.repository.base")
@EntityScan(basePackages = {"com.bareisha.producthunter.model.base"})
public class ProductRepositoryTestConfiguration {

}
