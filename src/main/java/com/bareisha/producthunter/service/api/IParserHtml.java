package com.bareisha.producthunter.service.api;

public interface IParserHtml {
    /**
     * Parse by url
     * @param path -  url
     */
    boolean parse(String path);

    /**
     * Parse by path to local file
     * @param path - path to local file
     */
    boolean parseFile(String path);
}
