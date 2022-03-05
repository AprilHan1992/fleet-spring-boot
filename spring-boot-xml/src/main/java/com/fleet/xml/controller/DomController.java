package com.fleet.xml.controller;

import com.fleet.xml.entity.Property;
import com.fleet.xml.entity.Protocol;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DOM 方式
 *
 * @author April Han
 */
@RestController
@RequestMapping("/dom")
public class DomController {

    @RequestMapping("/read")
    public List<Protocol> read() {
        List<Protocol> protocolList = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("classpath:xml/protocol.xml");
            NodeList nodeList = document.getElementsByTagName("protocol");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Protocol protocol = new Protocol();
                Node item = nodeList.item(i);
                for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        String name = node.getNodeName();
                        String value = node.getFirstChild().getNodeValue();
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
                            NodeList childNodeList = node.getChildNodes();
                            for (int j = 0; j < childNodeList.getLength(); j++) {
                                Node childItem = childNodeList.item(j);
                                if (childItem instanceof Element) {
                                    Property property = new Property();
                                    for (Node childNode = childItem.getFirstChild(); childNode != null; childNode = childNode.getNextSibling()) {
                                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                            String childName = childNode.getNodeName();
                                            String childValue = childNode.getFirstChild().getNodeValue();
                                            if ("value".equals(childName)) {
                                                property.setValue(childValue);
                                            }
                                            if ("desc".equals(childName)) {
                                                property.setDesc(childValue);
                                            }
                                        }
                                    }
                                    propertyList.add(property);
                                }
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
                }
                protocolList.add(protocol);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return protocolList;
    }
}
