package com.bareisha.producthunter.service.api;

import com.bareisha.producthunter.core.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> findAllProducts();
}
