package com.bareisha.producthunter.repository;

import com.bareisha.producthunter.model.base.Product;
import com.bareisha.producthunter.repository.base.IProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
@SpringBootTest(classes = {ProductRepositoryTestConfiguration.class})
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private IProductRepository productRepository;

    /**
     * CRUD operations for repository
     */
    @Test
    @Sql("classpath:sql/schema_test.sql")
    public void crudProductTest() {
        // Create example
        Product product = new Product();
        product.setCountry("USA");
        product.setDtUpdate(LocalDateTime.now());
        product.setPrice(new BigDecimal(2));
        product.setPriceDiscount(new BigDecimal(2));
        product.setUuid(UUID.randomUUID());
        product.setTitle("Title");

        final Product actualSave = productRepository.save(product);
        Assert.assertNotNull(actualSave.getId());

        final Product actualRead = productRepository.findById(actualSave.getId()).orElseGet(Product::new);
        Assert.assertEquals("Objects must be equal!", actualSave, actualRead);

        actualRead.setTitle("title2");
        final Product actualUpdate = productRepository.save(actualRead);
        Assert.assertEquals("Objects must be equal!", actualRead, actualUpdate);

        productRepository.delete(actualRead);
        Assert.assertEquals("Objects must be equal!", new Product(), productRepository.findById(actualRead.getId()).orElseGet(Product::new));
    }
}
