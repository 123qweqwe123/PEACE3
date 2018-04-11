package com.bdcor.pip.web.material.supp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MaterName {

	private String materlCode;
	private String materlName;
	private String helpCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String createBy;
	
	public String getMaterlCode() {
		return materlCode;
	}
	public void setMaterlCode(String materlCode) {
		this.materlCode = materlCode;
	}
	public String getMaterlName() {
		return materlName;
	}
	public void setMaterlName(String materlName) {
		this.materlName = materlName;
	}
	public String getHelpCode() {
		return helpCode;
	}
	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}
