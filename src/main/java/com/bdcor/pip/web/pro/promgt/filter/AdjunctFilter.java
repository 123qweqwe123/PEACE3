package com.bdcor.pip.web.pro.promgt.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class AdjunctFilter extends BaseFilter {

	private String projectId;
	private String creator;
	private String adjunctName;
	private String type;
	private String fileDesc;
	public void setFileDesc(String fileDesc)
	{
		this.fileDesc = fileDesc;
	}
	public String getFileDesc()
	{
		return fileDesc;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getAdjunctName() {
		return adjunctName;
	}
	public void setAdjunctName(String adjunctName) {
		this.adjunctName = adjunctName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
