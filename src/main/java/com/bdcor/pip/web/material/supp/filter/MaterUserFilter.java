package com.bdcor.pip.web.material.supp.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class MaterUserFilter extends BaseFilter{
	private String userName;
	private String userCode;
	private String lccCode;
	private String projectId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getLccCode() {
		return lccCode;
	}
	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	

}
