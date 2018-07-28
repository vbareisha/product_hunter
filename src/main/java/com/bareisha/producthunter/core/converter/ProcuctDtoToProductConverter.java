package com.bareisha.producthunter.core.converter;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProcuctDtoToProductConverter<S, T> implements IConverter<ProductDto, Product> {
    @Override
    public Product convert(ProductDto source) {
        Product target = new Product();
        target.setTitle(source.getTitle());
        target.setUuid(source.getUuid());
        target.setPriceDiscount(new BigDecimal(source.getPriceDiscount()));
        target.setPrice(new BigDecimal(source.getPrice()));
        target.setId(source.getId());
        target.setCountry(source.getCountry());
        target.setDtUpdate(source.getDtUpdate());
        return target;
    }
}
