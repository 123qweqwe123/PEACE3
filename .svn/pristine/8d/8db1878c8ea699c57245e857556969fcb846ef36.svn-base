package com.bdcor.pip.web.quality.filter;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipExpPlanFilter extends BaseFilter {
    private String id;

    private String provinceCode;

    private String lccCode;

    private String lccName;

    private Date planDate;

    private String expUserName;

    private String remark;

    private String createUserId;

    private Date createDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planSStartDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planSEndDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planEStartDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planEEndDate;
    

	public Date getPlanSStartDate()
	{
		return planSStartDate;
	}

	public void setPlanSStartDate(Date planSStartDate)
	{
		this.planSStartDate = planSStartDate;
	}

	public Date getPlanSEndDate()
	{
		return planSEndDate;
	}

	public void setPlanSEndDate(Date planSEndDate)
	{
		this.planSEndDate = planSEndDate;
	}

	public Date getPlanEStartDate()
	{
		return planEStartDate;
	}

	public void setPlanEStartDate(Date planEStartDate)
	{
		this.planEStartDate = planEStartDate;
	}

	public Date getPlanEEndDate()
	{
		return planEEndDate;
	}

	public void setPlanEEndDate(Date planEEndDate)
	{
		this.planEEndDate = planEEndDate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getLccCode() {
        return lccCode;
    }

    public void setLccCode(String lccCode) {
        this.lccCode = lccCode;
    }

    public String getLccName() {
        return lccName;
    }

    public void setLccName(String lccName) {
        this.lccName = lccName;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getExpUserName() {
        return expUserName;
    }

    public void setExpUserName(String expUserName) {
        this.expUserName = expUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}