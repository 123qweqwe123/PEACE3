package com.bdcor.pip.web.pro.promgt.domain;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Project {

	private String projectId;
	private String projectName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	private Integer isAutoStart;
	private Integer isAutoEnd;
	private String leader;
	private String workload;
	private String creator;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	private Integer isDelete;
	private String remark;
	private Integer status;
	private String shortName;
	private Integer isStarted;
	
	@Transient
	private String beginTimeString;
	@Transient
	private String endTimeString;
	@Transient
	private String createTimeString;
	
	public Project(){}
	
	public Project(String projectId, String projectName, Date beginTime, Date endTime,
			Integer isAutoStart, Integer isAutoEnd, String leader, String workload, String creator, Date createTime,
			Integer isDelete, String remark, Integer status, String shortName, Integer isStarted){
		this.projectId = projectId;
		this.projectName = projectName;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.isAutoStart = isAutoStart;
		this.isAutoEnd = isAutoEnd;
		this.leader = leader;
		this.workload = workload;
		this.creator = creator;
		this.createTime = createTime;
		this.isDelete = isDelete;
		this.remark = remark;
		this.status = status;
		this.shortName = shortName;
		this.isStarted = isStarted;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getIsAutoStart() {
		return isAutoStart;
	}
	public void setIsAutoStart(Integer isAutoStart) {
		this.isAutoStart = isAutoStart;
	}
	public Integer getIsAutoEnd() {
		return isAutoEnd;
	}
	public void setIsAutoEnd(Integer isAutoEnd) {
		this.isAutoEnd = isAutoEnd;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getWorkload() {
		return workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}

	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBeginTimeString() {
		return beginTimeString;
	}

	public void setBeginTimeString(String beginTimeString) {
		this.beginTimeString = beginTimeString;
	}

	public String getEndTimeString() {
		return endTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getIsStarted() {
		return isStarted;
	}

	public void setIsStarted(Integer isStarted) {
		this.isStarted = isStarted;
	}
	
}
