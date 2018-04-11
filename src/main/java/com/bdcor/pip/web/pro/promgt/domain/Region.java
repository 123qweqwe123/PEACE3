package com.bdcor.pip.web.pro.promgt.domain;

public class Region {

	private String regionId;
	private String projectId;
	private String organId;
	private String organName;
	private String principalId;
	private String contact;
	private Integer workload;
	
	public Region(){}
	
	public Region(String regionId, String projectId, String organId, String organName,
			String principalId, String contact, Integer workload) {
		super();
		this.regionId = regionId;
		this.projectId = projectId;
		this.organId = organId;
		this.organName = organName;
		this.principalId = principalId;
		this.contact = contact;
		this.workload = workload;
	}
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getWorkload() {
		return workload;
	}
	public void setWorkload(Integer workload) {
		this.workload = workload;
	}
	
}
