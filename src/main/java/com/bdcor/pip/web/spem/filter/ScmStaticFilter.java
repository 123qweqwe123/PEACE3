package com.bdcor.pip.web.spem.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class ScmStaticFilter extends BaseFilter{
	
	private String projectId;
	
	private String startTime = "2015-01-01";
	private String endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	private String areaCode;
	private String lccCode;
	
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getLccCode() {
		return lccCode;
	}
	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}
	
}