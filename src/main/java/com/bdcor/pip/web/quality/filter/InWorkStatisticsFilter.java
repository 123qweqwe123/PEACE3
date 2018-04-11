package com.bdcor.pip.web.quality.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class InWorkStatisticsFilter extends BaseFilter {

	private String projectId;
	private String userId;
	
	private String provinceCode;
	private String rccCode;
	private String lccCode;
	private String operatorId;
	private String operatorName;
	private String startDate;
	private String endDate;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getRccCode() {
		return rccCode;
	}
	public void setRccCode(String rccCode) {
		this.rccCode = rccCode;
	}
	public String getLccCode() {
		return lccCode;
	}
	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
