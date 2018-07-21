package com.bareisha.producthunter.service;

import com.bareisha.producthunter.core.exception.PageByUrlNotFoundException;
import com.bareisha.producthunter.service.api.IParserHtml;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
            System.out.println(document.body());
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
            System.out.println(document.body());
        } catch (IOException e) {
            log.error("Error in getting data from path = {}", path);
            log.error("Error: {}", e.getMessage());
            throw new PageByUrlNotFoundException(String.format("Page by path = %s not found!", path));
        }
        return false;
    }
}
