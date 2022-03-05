package com.fleet.xml.controller;

import com.fleet.xml.entity.Protocol;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SAX 方式
 *
 * @author April Han
 */
@RestController
@RequestMapping("/sax")
public class SaxController {

    @RequestMapping("/read")
    public List<Protocol> read() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            saxParser.parse("classpath:xml/protocol.xml", saxHandler);
            return saxHandler.getProtocolList();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
