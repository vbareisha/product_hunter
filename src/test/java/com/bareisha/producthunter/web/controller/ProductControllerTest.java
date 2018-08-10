package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.service.api.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.List;

import static com.bareisha.producthunter.utils.Util.getProductDtos;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {HealthStatusTestConfiguration.class})
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Autowired
    private IProductService<ProductDto> productService;

    @Test
    public void findAllProducts() throws Exception {
        setup();
        HttpHeaders headers = new HttpHeaders();

        List<ProductDto> expect = getProductDtos();
        expect.get(0).setId(1L);
        expect.get(1).setId(2L);
        when(productService.findAllProducts()).thenReturn(expect);

        MvcResult mvcResult = this.mockMvc
                .perform(get("/product/all").contentType(contentType).headers(headers))
                .andExpect(status().isOk()).andReturn();
        List<ProductDto> actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        Assert.assertEquals("Objects must be equal!", expect, actual);
    }
}