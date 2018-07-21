package com.bareisha.producthunter.service;

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

@Service
@Slf4j
public class EDostavkaParser implements IParserHtml {
    @Override
    public boolean parse(String path) {
        try {
            Document document = Jsoup.connect(path).get();
            getDocumentData(document);
        } catch (IOException e) {
            log.error("Error in getting data from path = {}", path);
            log.error("Error: {}", e.getMessage());
            throw new PageByUrlNotFoundException(String.format("Page by path = %s not found!", path));
        }
        return false;
    }

    @Override
    public boolean parseFile(String path) {
        try {
            File file = new File(path);
            Document document = Jsoup.parse(file, "UTF-8");
            getDocumentData(document);
        } catch (IOException e) {
            log.error("Error in getting data from path = {}", path);
            log.error("Error: {}", e.getMessage());
            throw new PageByUrlNotFoundException(String.format("Page by path = %s not found!", path));
        }
        return false;
    }

    private void getDocumentData(Document document) {
        Elements elementFormList = document.select("div.form_wrapper");
        for (Element element : elementFormList) {
            //title
            System.out.println(element.select("div.title").select("a[href]").text());
            //country
            System.out.println(element.select("div.small_country").text());
            //price
            System.out.println(element.select("div.prices__wrapper")
                    .select("div.services_wrap")
                    .select("div.prices_block")
                    .select("div.price_byn")
                    .select("div.price").text());

        }
    }
}
