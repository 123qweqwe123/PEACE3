package com.bdcor.pip.web.pro.promgt.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class RegionFilter extends BaseFilter {

	private String projectId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
