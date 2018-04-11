package com.bdcor.pip.web.sys.rbac.domain;

public class OrganType {

	private String organType;
	private String typeName;
	private String parentType;
	private Integer inUser;
	
	public OrganType() {
		super();
	}

	public OrganType(String organType, String typeName, String parentType,
			Integer inUser) {
		super();
		this.organType = organType;
		this.typeName = typeName;
		this.parentType = parentType;
		this.inUser = inUser;
	}

	public String getOrganType() {
		return organType;
	}

	public void setOrganType(String organType) {
		this.organType = organType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Integer getInUser() {
		return inUser;
	}

	public void setInUser(Integer inUser) {
		this.inUser = inUser;
	}
	
	
}
