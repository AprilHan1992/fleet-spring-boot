package com.fleet.xml.entity;

import java.io.Serializable;
import java.util.List;

public class Protocol implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据名称
	 */
	private String name;

	/**
	 * 数据标识
	 */
	private String identifier;

	/**
	 * 数据单位
	 */
	private String unit;

	/**
	 * 数据类型（1：字符，2：数值，3：浮点）
	 */
	private Integer type;

	/**
	 * 数据长度
	 */
	private Integer length;

	/**
	 * 保留字（0：否，1：是）
	 */
	private Integer reservedWord;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 属性值
	 */
	private List<Property> propertyList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getReservedWord() {
		return reservedWord;
	}

	public void setReservedWord(Integer reservedWord) {
		this.reservedWord = reservedWord;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
}
