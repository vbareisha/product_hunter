package com.bareisha.producthunter.service;

import com.bareisha.producthunter.service.api.IParserHtml;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

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
        }
        return false;
    }
}
