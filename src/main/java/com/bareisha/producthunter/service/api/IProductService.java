package com.bareisha.producthunter.service.api;

import com.bareisha.producthunter.core.dto.ProductDto;

import java.util.List;

public interface IProductService<T extends ProductDto> {
    /**
     * find all products
     * @return {@link List<T>}
     */
    List<T> findAllProducts();

    /**
     * save one product
     * @param source - {@link T}
     * @return - {@link T}
     */
    T saveProduct(T source);

    /**
     * Delete all data
     */
    void clearAll();
}
