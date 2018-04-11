package com.bdcor.pip.web.material.supp.filter;

import java.math.BigDecimal;
import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipMmsImscmdetalFilter extends BaseFilter {
	private Short id;

	private String projectId;

	private String imorderNo;

	private String materlinfoCode;

	private String archivesNo;

	private String materlBatch;

	private BigDecimal materlPrice;

	private BigDecimal wholesalePrice;

	private String importUnit;

	private String storeUnit;

	private Date periodValidity;

	private String manufacturerCode; // 生产厂商

	private String createBy;

	private Date createDate;

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

	public String getImorderNo() {
		return imorderNo;
	}

	public void setImorderNo(String imorderNo) {
		this.imorderNo = imorderNo;
	}

	public String getMaterlinfoCode() {
		return materlinfoCode;
	}

	public void setMaterlinfoCode(String materlinfoCode) {
		this.materlinfoCode = materlinfoCode;
	}

	public String getArchivesNo() {
		return archivesNo;
	}

	public void setArchivesNo(String archivesNo) {
		this.archivesNo = archivesNo;
	}

	public String getMaterlBatch() {
		return materlBatch;
	}

	public void setMaterlBatch(String materlBatch) {
		this.materlBatch = materlBatch;
	}

	public BigDecimal getMaterlPrice() {
		return materlPrice;
	}

	public void setMaterlPrice(BigDecimal materlPrice) {
		this.materlPrice = materlPrice;
	}

	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getImportUnit() {
		return importUnit;
	}

	public void setImportUnit(String importUnit) {
		this.importUnit = importUnit;
	}

	public String getStoreUnit() {
		return storeUnit;
	}

	public void setStoreUnit(String storeUnit) {
		this.storeUnit = storeUnit;
	}

	public Date getPeriodValidity() {
		return periodValidity;
	}

	public void setPeriodValidity(Date periodValidity) {
		this.periodValidity = periodValidity;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
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
}