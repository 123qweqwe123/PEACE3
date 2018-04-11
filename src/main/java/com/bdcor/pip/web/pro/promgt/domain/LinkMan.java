package com.bdcor.pip.web.pro.promgt.domain;

import javax.persistence.Transient;

public class LinkMan {

	private String linkManCode;
	private String username;
	private String phone;
	private String mobile;
	private String lccCode;
	private String address;

	private String department; // --科室
	@Transient
	private String lccName;
	private String email;
	private String lccRole;
	private String status;
	private String projectId;
	@Transient
	private String projectName;

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * department.
	 * 
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * department.
	 * 
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	public String getAddress() {
		return address;
	}

	public String getLinkManCode() {
		return linkManCode;
	}

	public void setLinkManCode(String linkManCode) {
		this.linkManCode = linkManCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLccRole() {
		return lccRole;
	}

	public void setLccRole(String lccRole) {
		this.lccRole = lccRole;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

}
