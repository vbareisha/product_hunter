package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import com.bareisha.producthunter.repository.base.IProductRepository;
import com.bareisha.producthunter.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Work with database
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements IProductService<ProductDto> {

    private final IProductRepository productRepository;

    private final IConverter<ProductDto, Product> productDtoProductIConverter;

    private final IConverter<Product, ProductDto> productProductDtoIConverter;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository,
                              @Qualifier("procuctDtoToProductConverter") IConverter<ProductDto, Product> productDtoProductIConverter,
                              @Qualifier("productToProductDtoConverter") IConverter<Product, ProductDto> productProductDtoIConverter) {
        this.productRepository = productRepository;
        this.productDtoProductIConverter = productDtoProductIConverter;
        this.productProductDtoIConverter = productProductDtoIConverter;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<ProductDto> resultList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            resultList.add(productProductDtoIConverter.convert(product));
        }
        return resultList;
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto source) {
        Product target = productDtoProductIConverter.convert(source);
        return productProductDtoIConverter.convert(productRepository.save(target));
    }
}
