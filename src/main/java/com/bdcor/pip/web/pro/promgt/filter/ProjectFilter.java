package com.bdcor.pip.web.pro.promgt.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class ProjectFilter  extends BaseFilter{
	
	private String projectName;
	private String creator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
