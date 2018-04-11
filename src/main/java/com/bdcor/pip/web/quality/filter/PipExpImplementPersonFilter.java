package com.bdcor.pip.web.quality.filter;

import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipExpImplementPersonFilter extends BaseFilter {
	private String id;

	private String personType;

	private String lccUserId;

	private String createBy;

	private Date createDate;

	private String updateBy;

	private Date updateDate;

	private String implementId;

	private String projectId;

	private String lccCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getLccUserId() {
		return lccUserId;
	}

	public void setLccUserId(String lccUserId) {
		this.lccUserId = lccUserId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getImplementId() {
		return implementId;
	}

	public void setImplementId(String implementId) {
		this.implementId = implementId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}
}