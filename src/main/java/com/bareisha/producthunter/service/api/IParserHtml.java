package com.bareisha.producthunter.service.api;

import com.bareisha.producthunter.core.dto.ProductDto;

import java.util.List;

public interface IParserHtml {
    /**
     * Parse by url
     * @param path -  url
     * @return {@link List<ProductDto>} list of product's after parsing
     */
    List<ProductDto> parse(String path);

    /**
     * Parse by path to local file
     * @param path - path to local file
     * @return {@link List<ProductDto>} list of product's after parsing
     */
    List<ProductDto> parseFile(String path);
}
