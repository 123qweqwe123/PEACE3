package com.bdcor.pip.data.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Option {
	
	
	private String id;
	
	
	private String datatype;
	
	private Long datatypeid;
	
	private String displayName;
	
	private Long order;
	
	
	private String datastyle;

	
	private String code;
	
	
	private List<Attribute> attributeList;

	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute(name = "datatype")
	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@XmlAttribute(name = "datatypeid")
	public Long getDatatypeid() {
		return datatypeid;
	}

	public void setDatatypeid(Long datatypeid) {
		this.datatypeid = datatypeid;
	}

	@XmlAttribute(name = "displayname")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@XmlAttribute(name = "order")
	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	@XmlAttribute(name = "datastyle")
	public String getDatastyle() {
		return datastyle;
	}

	public void setDatastyle(String datastyle) {
		this.datastyle = datastyle;
	}

	@XmlAttribute(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement(name="Attribute")
	public List<Attribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<Attribute> attributeList) {
		this.attributeList = attributeList;
	}
	
}
