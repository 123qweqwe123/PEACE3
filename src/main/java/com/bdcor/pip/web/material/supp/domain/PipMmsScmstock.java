package com.bdcor.pip.web.material.supp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PipMmsScmstock {
	private String lccName;
	private String stockName;
	private String materlName;
	
    private String projectId;

    private String lccCode;

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

    private String storeUnit;
    
    private Short stockState;
    
    /**
	 * stockState.  
	 * @return  the stockState   
	 */
	public Short getStockState() {
		return stockState;
	}

	/**
	 * stockState.  
	 * @param   stockState    the stockState to set  
	 */
	public void setStockState(Short stockState) {
		this.stockState = stockState;
	}

	public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
	 * lccName.  
	 * @return  the lccName   
	 */
	public String getLccName() {
		return lccName;
	}

	/**
	 * lccName.  
	 * @param   lccName    the lccName to set  
	 */
	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	/**
	 * stockName.  
	 * @return  the stockName   
	 */
	public String getStockName() {
		return stockName;
	}

	/**
	 * stockName.  
	 * @param   stockName    the stockName to set  
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	/**
	 * materlName.  
	 * @return  the materlName   
	 */
	public String getMaterlName() {
		return materlName;
	}

	/**
	 * materlName.  
	 * @param   materlName    the materlName to set  
	 */
	public void setMaterlName(String materlName) {
		this.materlName = materlName;
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

    public String getStoreUnit() {
        return storeUnit;
    }

    public void setStoreUnit(String storeUnit) {
        this.storeUnit = storeUnit;
    }
}