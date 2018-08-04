package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.core.exception.PageByUrlNotFoundException;
import com.bareisha.producthunter.model.base.Product;
import com.bareisha.producthunter.repository.base.IProductRepository;
import com.bareisha.producthunter.service.api.IParserHtml;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bareisha.producthunter.utils.Util.getProductDtos;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EDostavkaParserTestConfiguration.class)
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EDostavkaParserTest {

    @Autowired
    private IParserHtml parserHtml;

    @Autowired
    private IConverter<ProductDto, Product> productDtoProductIConverter;

    @MockBean
    private IProductRepository productRepository;

    @Test(expected = PageByUrlNotFoundException.class)
    public void parserFileAndGetException() {
        parserHtml.parseFile("testlocalpath.html");
    }

    @SuppressWarnings("Duplicates")
    private void setup() {
        Product productOne = productDtoProductIConverter.convert(getProductDtos().get(0));
        productOne.setUuid(UUID.randomUUID());
        productOne.setDtUpdate(LocalDateTime.now());
        Product productTwo = productDtoProductIConverter.convert(getProductDtos().get(1));
        productTwo.setUuid(UUID.randomUUID());
        productTwo.setDtUpdate(LocalDateTime.now());

        when(productRepository.save(any(Product.class))).thenReturn(productOne);
        when(productRepository.save(any(Product.class))).thenReturn(productTwo);
    }

    @Test
    public void parseFile() {
        setup();
        List<ProductDto> expectedProductList = getProductDtos();
        expectedProductList.forEach(productDto -> {
            productDto.setUuid(null);
            productDto.setDtUpdate(null);
        });

        URL path = this.getClass().getClassLoader().getResource("html/" + "test.html");
        List<ProductDto> actualProductList = new ArrayList<>();
        if (path != null) {
            actualProductList = parserHtml.parseFile(path.getPath());
            actualProductList.forEach(productDto -> {
                productDto.setUuid(null);
                productDto.setDtUpdate(null);
            });
        }
        Assert.assertEquals(actualProductList, expectedProductList);
    }
}
