package com.bdcor.pip.web.msg.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class MsgReplyFilter extends BaseFilter  {
	
	private String projectId;
	
	private String userId;
	
	private String lccCode;
	
	private String patientId;
	
	private String patientIdEq;
	
	private String patientName;
	
	private Integer group;
	
	private String replyTime;
	
	private String replyTimeStart;
	
	private String replyTimeEnd;
	
	private String isDiabetes;

	public String getIsDiabetes() {
		return isDiabetes;
	}

	public void setIsDiabetes(String isDiabetes) {
		this.isDiabetes = isDiabetes;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getPatientIdEq() {
		return patientIdEq;
	}

	public void setPatientIdEq(String patientIdEq) {
		this.patientIdEq = patientIdEq;
	}

	public String getReplyTimeStart() {
		return replyTimeStart;
	}

	public void setReplyTimeStart(String replyTimeStart) {
		this.replyTimeStart = replyTimeStart;
	}

	public String getReplyTimeEnd() {
		return replyTimeEnd;
	}

	public void setReplyTimeEnd(String replyTimeEnd) {
		this.replyTimeEnd = replyTimeEnd;
	}
	
}
