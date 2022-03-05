package com.fleet.xml.entity;

import java.io.Serializable;

public class Property implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;

	private String desc;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
