package com.bdcor.pip.web.material.supp.domain;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Supplier {

	private String supplierCode;
	@Transient
	private String supplierCode1;
	private String supplierName;
	private String supplierClass;
	private String localityFlag;
	private Integer creditClass;
	private String address;
	private String zipCode;
	private String contactName;
	private String contactTel;
	private String contactEmail;
	private String businessTel;
	private String helpCode;
	private String wangCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date qualificationDisableDate;
	@Transient
	private String qualificationDisableDateStr;
	private String status;
	
	public Supplier() {
		super();
	}

	public Supplier(String supplierCode, String supplierName,
			String supplierClass, String localityFlag, Integer creditClass,
			String address, String zipCode, String contactName,
			String contactTel, String contactEmail, String businessTel,
			String helpCode, String wangCode, Date qualificationDisableDate,
			String status) {
		super();
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
		this.supplierClass = supplierClass;
		this.localityFlag = localityFlag;
		this.creditClass = creditClass;
		this.address = address;
		this.zipCode = zipCode;
		this.contactName = contactName;
		this.contactTel = contactTel;
		this.contactEmail = contactEmail;
		this.businessTel = businessTel;
		this.helpCode = helpCode;
		this.wangCode = wangCode;
		this.qualificationDisableDate = qualificationDisableDate;
		this.status = status;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getLocalityFlag() {
		return localityFlag;
	}

	public void setLocalityFlag(String localityFlag) {
		this.localityFlag = localityFlag;
	}

	public String getSupplierClass() {
		return supplierClass;
	}

	public void setSupplierClass(String supplierClass) {
		this.supplierClass = supplierClass;
	}

	public Integer getCreditClass() {
		return creditClass;
	}

	public void setCreditClass(Integer creditClass) {
		this.creditClass = creditClass;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getBusinessTel() {
		return businessTel;
	}

	public void setBusinessTel(String businessTel) {
		this.businessTel = businessTel;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getWangCode() {
		return wangCode;
	}

	public void setWangCode(String wangCode) {
		this.wangCode = wangCode;
	}

	public Date getQualificationDisableDate() {
		return qualificationDisableDate;
	}

	public void setQualificationDisableDate(Date qualificationDisableDate) {
		this.qualificationDisableDate = qualificationDisableDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupplierCode1() {
		return supplierCode1;
	}

	public void setSupplierCode1(String supplierCode1) {
		this.supplierCode1 = supplierCode1;
	}

	public String getQualificationDisableDateStr() {
		return qualificationDisableDateStr;
	}

	public void setQualificationDisableDateStr(String qualificationDisableDateStr) {
		this.qualificationDisableDateStr = qualificationDisableDateStr;
	}
	
}
