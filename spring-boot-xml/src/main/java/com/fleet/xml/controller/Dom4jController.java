package com.fleet.xml.controller;

import com.fleet.xml.entity.Property;
import com.fleet.xml.entity.Protocol;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * DOM4J 方式
 *
 * @author April Han
 */
@RestController
@RequestMapping("/dom4j")
public class Dom4jController {

    @RequestMapping("/read")
    public List<Protocol> read() {
        List<Protocol> protocolList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read("classpath:xml/protocol.xml");
            Element rootElement = document.getRootElement();
            Iterator childrenIterator = rootElement.elementIterator();
            while (childrenIterator.hasNext()) {
                Protocol protocol = new Protocol();
                Iterator childIterator = ((Element) childrenIterator.next()).elementIterator();
                while (childIterator.hasNext()) {
                    Element childElement = (Element) childIterator.next();
                    String name = childElement.getName();
                    String value = childElement.getStringValue();
                    if ("name".equals(name)) {
                        protocol.setName(value);
                    }
                    if ("identifier".equals(name)) {
                        protocol.setIdentifier(value);
                    }
                    if ("unit".equals(name)) {
                        protocol.setUnit(value);
                    }
                    if ("type".equals(name)) {
                        protocol.setType(Integer.parseInt(value));
                    }
                    if ("length".equals(name)) {
                        protocol.setLength(Integer.parseInt(value));
                    }
                    if ("propertyList".equals(name)) {
                        List<Property> propertyList = new ArrayList<>();
                        Iterator cIterator = childElement.elementIterator();
                        while (cIterator.hasNext()) {
                            Property property = new Property();
                            Iterator iterator = ((Element) cIterator.next()).elementIterator();
                            while (iterator.hasNext()) {
                                Element element = (Element) iterator.next();
                                String childName = element.getName();
                                String childValue = element.getStringValue();
                                if ("value".equals(childName)) {
                                    property.setValue(childValue);
                                }
                                if ("desc".equals(childName)) {
                                    property.setDesc(childValue);
                                }
                            }
                            propertyList.add(property);
                        }
                        protocol.setPropertyList(propertyList);
                    }
                    if ("reservedWord".equals(name)) {
                        protocol.setReservedWord(Integer.parseInt(value));
                    }
                    if ("remark".equals(name)) {
                        protocol.setRemark(value);
                    }
                }
                protocolList.add(protocol);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return protocolList;
    }
}
