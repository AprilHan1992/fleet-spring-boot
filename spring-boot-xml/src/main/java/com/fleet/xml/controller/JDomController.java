package com.fleet.xml.controller;

import com.fleet.xml.entity.Property;
import com.fleet.xml.entity.Protocol;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDOM 方式
 *
 * @author April Han
 */
@RestController
@RequestMapping("/jdom")
public class JDomController {

    @RequestMapping("/read")
    public List<Protocol> read() {
        List<Protocol> protocolList = new ArrayList<>();
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build("classpath:xml/protocol.xml");
            Element rootElement = document.getRootElement();
            List<Element> childrenList = rootElement.getChildren();
            for (Element childrenElement : childrenList) {
                Protocol protocol = new Protocol();
                List<Element> childList = childrenElement.getChildren();
                for (Element childElement : childList) {
                    if (childElement.getChildren() != null && childElement.getChildren().size() != 0) {
                        System.out.println("");
                    }
                    String name = childElement.getName();
                    String value = childElement.getValue();
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
                        List<Element> cList = childElement.getChildren();
                        for (Element cElement : cList) {
                            Property property = new Property();
                            List<Element> list = cElement.getChildren();
                            for (Element e : list) {
                                String childName = e.getName();
                                String childValue = e.getValue();
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
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return protocolList;
    }
}
