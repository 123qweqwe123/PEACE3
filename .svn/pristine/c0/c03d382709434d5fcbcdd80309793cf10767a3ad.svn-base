package com.bdcor.pip.web.quality.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PipExpPlan {
    private String id;

    private String provinceCode;
    
    private String lccCode;

    private String lccName;
    
    private String userName;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planStartDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planEndDate;
    private String planEndDateStr;    //--字符串展示日期
    private String planStartDateStr;  //--字符串展示日期
    public void setPlanEndDateStr(String planEndDateStr)
	{
		this.planEndDateStr = planEndDateStr;
	}
    public String getPlanEndDateStr()
	{
		return planEndDateStr;
	}
    public void setPlanEndDate(Date planEndDate)
	{
		this.planEndDate = planEndDate;
	}
    public Date getPlanEndDate()
	{
		return planEndDate;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public String getPlanStartDateStr() {
		return planStartDateStr;
	}

	public void setPlanStartDateStr(String planDateStr) {
		this.planStartDateStr = planDateStr;
	}

	private String expUserName;

    private String remark;

    private String createUserId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    
    private String provinceName;
    
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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

    public Date getPlanStartDate()
	{
		return planStartDate;
	}

    public void setPlanStartDate(Date planStartDate)
	{
		this.planStartDate = planStartDate;
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