package com.bdcor.pip.web.pro.promgt.domain;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Evaluation {

	private String id;
	private String lccCode;
	private String evaluation;
	private String remark;
	private String createBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	private Integer isRemoved;
	private Integer evaluationLevel;
	
	@Transient
	private String lccName;
	
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
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getIsRemoved() {
		return isRemoved;
	}
	public void setIsRemoved(Integer isRemoved) {
		this.isRemoved = isRemoved;
	}
	public Integer getEvaluationLevel() {
		return evaluationLevel;
	}
	public void setEvaluationLevel(Integer evaluationLevel) {
		this.evaluationLevel = evaluationLevel;
	}
	public String getLccName() {
		return lccName;
	}
	public void setLccName(String lccName) {
		this.lccName = lccName;
	}
	
}
