package com.bdcor.pip.web.data.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipCommRccFilter extends BaseFilter {

	
	private String  projectId;
	private String  rccCode;
	private String  rccName; 
	private String  countryCode;
	private String  areaCode;
	private String  englishName;
	private String  address;
	private String   helpCode;
	private String   orgCode;
	private String   manaMan;
	private String   departMent;
	private String   unit;
	
	public PipCommRccFilter(){}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRccCode() {
		return rccCode;
	}

	public void setRccCode(String rccCode) {
		this.rccCode = rccCode;
	}

	public String getRccName() {
		return rccName;
	}

	public void setRccName(String rccName) {
		this.rccName = rccName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getManaMan() {
		return manaMan;
	}

	public void setManaMan(String manaMan) {
		this.manaMan = manaMan;
	}

	public String getDepartMent() {
		return departMent;
	}

	public void setDepartMent(String departMent) {
		this.departMent = departMent;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
	
	
	
	
	
	
	
}
