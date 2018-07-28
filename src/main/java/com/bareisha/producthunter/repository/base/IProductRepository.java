package com.bareisha.producthunter.repository.base;

import com.bareisha.producthunter.model.base.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
}
