package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.converter.api.IConverter;
import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.model.base.Product;
import com.bareisha.producthunter.repository.base.IProductRepository;
import com.bareisha.producthunter.service.api.IProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.bareisha.producthunter.utils.Util.getProductDtos;
import static java.util.stream.Collectors.toList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductServiceTestConfiguration.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @MockBean
    private IProductRepository productRepository;

    @Autowired
    private IProductService<ProductDto> productService;

    @Autowired
    private IConverter<ProductDto, Product> productDtoProductIConverter;

    @Test
    public void findAllProducts() {
        List<ProductDto> expectList = getProductDtos();
        when(productRepository.findAll()).thenReturn(expectList
                .stream()
                .map(productDtoProductIConverter::convert)
                .collect(toList()));
        List<ProductDto> actual = productService.findAllProducts();
        Assert.assertEquals("List must be equal!", expectList.get(0), actual.get(0));
        Assert.assertEquals("List must be equal!", expectList.get(1), actual.get(1));
    }

    @Test
    public void saveProduct() {
        List<ProductDto> startList = getProductDtos();
        Product productOne = productDtoProductIConverter.convert(startList.get(0));
        productOne.setId(1L);
        when(productRepository.save(any(Product.class))).thenReturn(productOne);

        ProductDto expect = startList.get(0);
        expect.setId(1L);
        ProductDto actual = productService.saveProduct(startList.get(0));

        Assert.assertEquals("Objects must be equal!", expect, actual);
    }

    @Test
    public void clearAll() {
        productService.clearAll();
        Assert.assertTrue(true);
    }
}
