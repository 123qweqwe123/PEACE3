package com.bdcor.pip.web.material.supp.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class StockFilter extends BaseFilter {
	private String materlName;
	private String stockName;
	private String projectId;
	private String lccCode;
	private String userId;
	
	private String isZero;
	public void setIsZero(String isZero)
	{
		this.isZero = isZero;
	}
	public String getIsZero()
	{
		return isZero;
	}
	
	private String archivesNo ; //设备编号
	
	public void setArchivesNo(String archivesNo)
	{
		this.archivesNo = archivesNo;
	}
	public String getArchivesNo()
	{
		return archivesNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getMaterlName() {
		if ("".equals(materlName))
			return null;
		return materlName;
	}

	public void setMaterlName(String materlName) {
		this.materlName = materlName;
	}

	public String getStockName() {
		if ("".equals(stockName))
			return null;
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
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
