package com.bdcor.pip.web.material.supp.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.pentaho.di.trans.steps.getpreviousrowfield.GetPreviousRowField;
import org.springframework.format.annotation.DateTimeFormat;

import com.bdcor.pip.client.tools.DateUtils;

public class PipMmsExscmdetal {
    private String id;

    private String projectId;

    private String exorderNo;

    private String materlinfoCode;

    private String archivesNo;

    private String materlBatch;

    private BigDecimal materlPrice;

    private BigDecimal wholesalePrice;

    private String storeUnit;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date periodValidity;
    
    private String periodValidityStr ;
    public String getPeriodValidityStr() {
		return DateUtils.fromatDateTime("yyyy-MM-dd", getPeriodValidity());
	}
    private String manufacturerCode;
    
    private String createBy;
    private String createDateStr;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    public String getCreateDateStr() {
		return DateUtils.fromatDateTime("yyyy-MM-dd", getCreateDate());
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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