package com.bdcor.pip.web.material.supp.domain;

import java.util.Date;

import javax.persistence.Transient;

public class MaterlInfo {

	private String materlInfoCode;
	private String materlCode;
	private String materlName;
	private String storeUnit;
	private String materlSpec;
	private String supplierCode;
	private String helpCode;
	private Date createDate;
	private String createBy;
	private Integer isRemoved;
	private String classCode;
	private String projectId;
	@Transient
	private String className;
	@Transient
	private String supplierName;
	
	public String getMaterlInfoCode() {
		return materlInfoCode;
	}
	public void setMaterlInfoCode(String materlInfoCode) {
		this.materlInfoCode = materlInfoCode;
	}
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
	public String getStoreUnit() {
		return storeUnit;
	}
	public void setStoreUnit(String storeUnit) {
		this.storeUnit = storeUnit;
	}
	public String getMaterlSpec() {
		return materlSpec;
	}
	public void setMaterlSpec(String materlSpec) {
		this.materlSpec = materlSpec;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	public Integer getIsRemoved() {
		return isRemoved;
	}
	public void setIsRemoved(Integer isRemoved) {
		this.isRemoved = isRemoved;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}