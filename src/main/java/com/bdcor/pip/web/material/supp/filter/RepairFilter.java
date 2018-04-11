package com.bdcor.pip.web.material.supp.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class RepairFilter extends BaseFilter {

	private String projectId;
	private String archivesNo;
	private Integer repairState;
	private String materlName;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getArchivesNo() {
		return archivesNo;
	}
	public void setArchivesNo(String archivesNo) {
		this.archivesNo = archivesNo;
	}
	public Integer getRepairState() {
		return repairState;
	}
	public void setRepairState(Integer repairState) {
		this.repairState = repairState;
	}
	public String getMaterlName() {
		return materlName;
	}
	public void setMaterlName(String materlName) {
		this.materlName = materlName;
	}
	
}
