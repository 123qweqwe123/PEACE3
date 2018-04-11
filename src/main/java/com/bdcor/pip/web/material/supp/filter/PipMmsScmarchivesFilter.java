package com.bdcor.pip.web.material.supp.filter;

import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipMmsScmarchivesFilter extends BaseFilter {
	private Short id;

	private String projectId;

	private String archivesNo;

	private String bloodpackageCode;

	private Date periodValidity;

	private Short packageState;

	private String createBy;

	private Date createDate;

	private String materlBatch;
	
	private String stockCode;	//--库房编码
	
	private String userId; //--用户id
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockCode() {
		return stockCode;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

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

	public String getBloodpackageCode() {
		return bloodpackageCode;
	}

	public void setBloodpackageCode(String bloodpackageCode) {
		this.bloodpackageCode = bloodpackageCode;
	}

	public Date getPeriodValidity() {
		return periodValidity;
	}

	public void setPeriodValidity(Date periodValidity) {
		this.periodValidity = periodValidity;
	}

	public Short getPackageState() {
		return packageState;
	}

	public void setPackageState(Short packageState) {
		this.packageState = packageState;
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

	public String getMaterlBatch() {
		return materlBatch;
	}

	public void setMaterlBatch(String materlBatch) {
		this.materlBatch = materlBatch;
	}
}