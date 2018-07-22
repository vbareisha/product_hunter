package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.core.exception.PageByUrlNotFoundException;
import com.bareisha.producthunter.service.api.IParserHtml;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;

@SpringBootTest(classes = EDostavkaParserTestConfiguration.class)
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
public class EDostavkaParserTest {

    @Autowired
    private IParserHtml parserHtml;

    @Test
    public void parseFileTest() {
        URL path = this.getClass().getClassLoader().getResource("html/" + "test.html");
        if (path != null) {
            List<ProductDto> actualResulList = parserHtml.parseFile(path.getPath());
            System.out.println(actualResulList);
        }
    }

    @Test(expected = PageByUrlNotFoundException.class)
    public void parserFileAndGetException() {
        parserHtml.parseFile("testlocalpath.html");
    }
}
