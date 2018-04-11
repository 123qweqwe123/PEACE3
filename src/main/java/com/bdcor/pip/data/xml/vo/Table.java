package com.bdcor.pip.data.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Table {
	
	private String displayname;
	
	private List<Tr> trList;

	@XmlElement(name="displayname")
	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	
	@XmlElement(name="tr")
	public List<Tr> getTrList() {
		return trList;
	}

	public void setTrList(List<Tr> trList) {
		this.trList = trList;
	}

	
	
}
