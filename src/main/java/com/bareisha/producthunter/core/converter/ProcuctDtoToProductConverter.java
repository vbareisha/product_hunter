package com.bareisha.producthunter.core.converter;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class ProcuctDtoToProductConverter<S, T> implements IConverter<ProductDto, Product> {
    @Override
    public Product convert(ProductDto source) {
        log.debug("Convert {}", source);
        Product target = new Product();
        target.setTitle(source.getTitle());
        target.setUuid(source.getUuid());
        if (source.getPriceDiscount() != null) {
            target.setPriceDiscount(new BigDecimal(source.getPriceDiscount()));
        }
        if (source.getPrice() != null) {
            target.setPrice(new BigDecimal(source.getPrice()));
        }
        target.setId(source.getId());
        target.setCountry(source.getCountry());
        target.setDtUpdate(source.getDtUpdate());
        target.setImage(source.getImage());
        log.debug("Result {}", target);
        return target;
    }
}
