package com.bareisha.producthunter.web.controller;

import com.bareisha.producthunter.service.api.IParserHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/parser")
public class ParserController {

    private final IParserHtml parserHtml;

    @Autowired
    public ParserController(IParserHtml parserHtml) {
        this.parserHtml = parserHtml;
    }
    //todo test
    @GetMapping(value = "/edostavka")
    public ResponseEntity<?> parserEDostavka(@RequestParam(name = "url") String url) {
        return new ResponseEntity<>(parserHtml.parse(url), OK);
    }
}
