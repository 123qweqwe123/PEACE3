package com.bdcor.pip.web.pro.promgt.domain;

import java.util.Date;

public class PipCommLccEthical {

	private String id;
	private String lccCode;
	private String ethicalType;
	private Date ethicalEffectiveDate;
	private Date ethicalDisableDate;
	private String ethicalPaperCode;
	private Date createDate;
	private String createBy;
	private String lccName;
	private String areaName;

	public PipCommLccEthical() {

	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getLccName() {
		return lccName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getEthicalType() {
		return ethicalType;
	}

	public void setEthicalType(String ethicalType) {
		this.ethicalType = ethicalType;
	}

	public Date getEthicalEffectiveDate() {
		return ethicalEffectiveDate;
	}

	public void setEthicalEffectiveDate(Date ethicalEffectiveDate) {
		this.ethicalEffectiveDate = ethicalEffectiveDate;
	}

	public Date getEthicalDisableDate() {
		return ethicalDisableDate;
	}

	public void setEthicalDisableDate(Date ethicalDisableDate) {
		this.ethicalDisableDate = ethicalDisableDate;
	}

	public String getEthicalPaperCode() {
		return ethicalPaperCode;
	}

	public void setEthicalPaperCode(String ethicalPaperCode) {
		this.ethicalPaperCode = ethicalPaperCode;
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
