package com.bdcor.pip.web.material.supp.filter;

import java.math.BigDecimal;
import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipMmsScmstockFilter extends BaseFilter {
	private String isZero;
	private String stockName;
	private String projectId;
	private String lccCode;
	private String userId;

	private String materlinfoCode;

	private String stockCode;

	private String archivesNo;

	private String materlBatch;

	private BigDecimal materlPrice;

	private BigDecimal wholesalePrice;

	private BigDecimal stockNum;

	private Date periodValidity;

	private String manufacturerCode;

	private String createBy;

	private Date createDate;

	private String imorderNo;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockName() {
		return stockName;
	}

	public void setIsZero(String isZero) {
		this.isZero = isZero;
	}

	public String getIsZero() {
		return isZero;
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

	public String getMaterlinfoCode() {
		return materlinfoCode;
	}

	public void setMaterlinfoCode(String materlinfoCode) {
		this.materlinfoCode = materlinfoCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
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

	public BigDecimal getStockNum() {
		return stockNum;
	}

	public void setStockNum(BigDecimal stockNum) {
		this.stockNum = stockNum;
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

	public String getImorderNo() {
		return imorderNo;
	}

	public void setImorderNo(String imorderNo) {
		this.imorderNo = imorderNo;
	}
}