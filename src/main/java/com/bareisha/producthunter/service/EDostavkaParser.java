package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.dto.ProductDto;
import com.bareisha.producthunter.core.exception.PageByUrlNotFoundException;
import com.bareisha.producthunter.service.api.IParserHtml;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EDostavkaParser implements IParserHtml {

    @Override
    public List<ProductDto> parse(String url) {
        List<ProductDto> resultList;
        try {
            Document document = Jsoup.connect(url).get();
            resultList = getDocumentData(document);
        } catch (IOException e) {
            log.error("Error in getting data from path = {}", url);
            log.error("Error: {}", e.getMessage());
            throw new PageByUrlNotFoundException(String.format("Page by path = %s not found!", url));
        }
        return resultList;
    }

    @Override
    public List<ProductDto> parseFile(String path) {
        List<ProductDto> resultList;
        try {
            File file = new File(path);
            Document document = Jsoup.parse(file, "UTF-8");
            resultList = getDocumentData(document);
        } catch (IOException e) {
            log.error("Error in getting data from path = {}", path);
            log.error("Error: {}", e.getMessage());
            throw new PageByUrlNotFoundException(String.format("Page by path = %s not found!", path));
        }
        return resultList;
    }

    private List<ProductDto> getDocumentData(Document document) {
        Elements elementFormList = document.select("div.form_wrapper");
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Element element : elementFormList) {
            ProductDto product = new ProductDto();
            //title
            product.setTitle(element.select("div.title").select("a[href]").text());
            //country
            product.setCountry(element.select("div.small_country").text());
            //price
            String[] tempPriceList = element.select("div.prices__wrapper")
                    .select("div.services_wrap")
                    .select("div.prices_block")
                    .select("div.price_byn")
                    .select("div.price").text().split(" ");
            if (tempPriceList.length > 1) {
                product.setPrice(getBigDecimalPriceFromString(tempPriceList[1]).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
                product.setPriceDiscount(getBigDecimalPriceFromString(tempPriceList[0]).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
            } else {
                product.setPrice(getBigDecimalPriceFromString(tempPriceList[0]).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
            }
            product.setDtUpdate(LocalDateTime.now());
            product.setUuid(UUID.randomUUID());
            productDtoList.add(product);
        }
        return productDtoList;
    }

    private BigDecimal getBigDecimalPriceFromString(String itemPrice) {
        String mainPrice = itemPrice.substring(0, itemPrice.indexOf("р"));
        String digitPrice = itemPrice.substring(itemPrice.indexOf("р") + 2, itemPrice.indexOf("к"));
        return new BigDecimal(Double.valueOf(mainPrice + "." + digitPrice));
    }
}
