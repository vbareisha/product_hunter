package com.bareisha.producthunter.core.converter;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductToProductDtoConverter<S, T> implements IConverter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product source) {
        log.debug("Convert {}", source);
        ProductDto target = new ProductDto();
        target.setTitle(source.getTitle());
        target.setUuid(source.getUuid());
        if (source.getPriceDiscount() != null) {
            target.setPriceDiscount(source.getPriceDiscount().doubleValue());

        }
        if (source.getPrice() != null) {
            target.setPrice(source.getPrice().doubleValue());
        }
        target.setId(source.getId());
        target.setCountry(source.getCountry());
        target.setDtUpdate(source.getDtUpdate());
        target.setImage(source.getImage());
        log.debug("Result {}", target);
        return target;
    }
}
