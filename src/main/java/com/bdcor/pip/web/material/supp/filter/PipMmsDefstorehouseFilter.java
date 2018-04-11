package com.bdcor.pip.web.material.supp.filter;

import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipMmsDefstorehouseFilter extends BaseFilter {
    private Short id;

    private String projectId;

    private String exorderNo;

    private String lccCode;

    private String stockCode;

    private String createBy;

    private Date createDate;

    private String stockName;
    
    private String userId;
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
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

    public String getExorderNo() {
        return exorderNo;
    }

    public void setExorderNo(String exorderNo) {
        this.exorderNo = exorderNo;
    }

    public String getLccCode() {
        return lccCode;
    }

    public void setLccCode(String lccCode) {
        this.lccCode = lccCode;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}