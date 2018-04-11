package com.bdcor.pip.web.spem.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class CheckInListFilter extends BaseFilter{
	
	private String projectId;
	
	private String checkInListNo;
	private String boxCode;
	private String lccCode;
	private String userId;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCheckInListNo() {
		return checkInListNo;
	}
	public void setCheckInListNo(String checkInListNo) {
		this.checkInListNo = checkInListNo;
	}
	public String getBoxCode() {
		return boxCode;
	}
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}
	public String getLccCode() {
		return lccCode;
	}
	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}