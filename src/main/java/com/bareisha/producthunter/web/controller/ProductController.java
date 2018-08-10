package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.service.api.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final IProductService<ProductDto> productService;

    @Autowired
    public ProductController(IProductService<ProductDto> productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAllProducts() {
        log.debug("FindAll()...");
        return new ResponseEntity<>(productService.findAllProducts(), OK);
    }
}
