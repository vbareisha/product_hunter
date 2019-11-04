package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.service.api.IParserHtml;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.bareisha.producthunter.utils.Util.getProductDtos;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ParserControllerTestConfiguration.class})
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ParserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Autowired
    private IParserHtml parserHtml;

    @Test
    public void parserEDostavka() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        List<ProductDto> expect = getProductDtos();
        when(parserHtml.parse(anyString())).thenReturn(expect);

        MvcResult mvcResult = this.mockMvc
                .perform(get("/parser/edostavka?url=test").contentType(contentType).headers(headers))
                .andExpect(status().isOk()).andReturn();

        List<ProductDto> actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        Assert.assertEquals("Objects must be equal!", expect, actual);
    }
}
