package com.bareisha.producthunter.core.converter;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductToProductDtoConverter<S, T> implements IConverter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product source) {
        ProductDto target = new ProductDto();
        target.setTitle(source.getTitle());
        target.setUuid(source.getUuid());
        target.setPriceDiscount(source.getPriceDiscount().doubleValue());
        target.setPrice(source.getPrice().doubleValue());
        target.setId(source.getId());
        target.setCountry(source.getCountry());
        target.setDtUpdate(source.getDtUpdate());
        return target;
    }
}
