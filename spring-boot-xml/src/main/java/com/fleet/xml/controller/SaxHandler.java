package com.fleet.xml.controller;

import com.fleet.xml.entity.Property;
import com.fleet.xml.entity.Protocol;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    private String name;

    private Protocol protocol;

    private Property property;

    private List<Protocol> protocolList = new ArrayList<>();

    private List<Property> propertyList = new ArrayList<>();

    public List<Protocol> getProtocolList() {
        return protocolList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        name = qName;
        if ("protocol".equals(name)) {
            protocol = new Protocol();
        }
        if ("property".equals(name)) {
            property = new Property();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("protocol".equals(qName)) {
            if (propertyList.size() != 0) {
                protocol.setPropertyList(propertyList);
            }
            propertyList = new ArrayList<>();
            protocolList.add(protocol);
        }
        if ("property".equals(qName)) {
            propertyList.add(property);
        }
        name = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
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
        if ("value".equals(name)) {
            property.setValue(value);
        }
        if ("desc".equals(name)) {
            property.setDesc(value);
        }
        if ("reservedWord".equals(name)) {
            protocol.setReservedWord(Integer.parseInt(value));
        }
        if ("remark".equals(name)) {
            protocol.setRemark(value);
        }
    }
}
