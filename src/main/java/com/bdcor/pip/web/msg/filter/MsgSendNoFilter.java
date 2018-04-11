package com.bdcor.pip.web.msg.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class MsgSendNoFilter  {
	
	private String projectId;
	
	private String userId;
	
	private String lccCode;
	
	private String patientId;
	
	private String patientName;
	
	private Integer group;
	private String reportCode;
	private String realSendTimeStart;
	
	private String realSendTimeEnd;
	
	private String preSendTime;
	
	private String realSendTime;
	
	private String preSendTimeStart;
	
	private String preSendTimeEnd;
	
	private Integer state;
	
	private Integer isDelete;
	
	private String sendId;
	
	private String sendResult;
	
	private String startDate;
	private String endDate;
	
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public String getStartDate()
	{
		return startDate;
	}
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	public String getEndDate()
	{
		return endDate;
	}
	public void setSendResult(String sendResult)
	{
		this.sendResult = sendResult;
	}
	public String getSendResult()
	{
		return sendResult;
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

	public String getRealSendTimeStart() {
		return realSendTimeStart;
	}

	public void setRealSendTimeStart(String realSendTimeStart) {
		this.realSendTimeStart = realSendTimeStart;
	}

	public String getRealSendTimeEnd() {
		return realSendTimeEnd;
	}

	public void setRealSendTimeEnd(String realSendTimeEnd) {
		this.realSendTimeEnd = realSendTimeEnd;
	}

	public String getPreSendTimeStart() {
		return preSendTimeStart;
	}

	public void setPreSendTimeStart(String preSendTimeStart) {
		this.preSendTimeStart = preSendTimeStart;
	}

	public String getPreSendTimeEnd() {
		return preSendTimeEnd;
	}

	public void setPreSendTimeEnd(String preSendTimeEnd) {
		this.preSendTimeEnd = preSendTimeEnd;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getPreSendTime() {
		return preSendTime;
	}

	public void setPreSendTime(String preSendTime) {
		this.preSendTime = preSendTime;
	}

	public String getRealSendTime() {
		return realSendTime;
	}

	public void setRealSendTime(String realSendTime) {
		this.realSendTime = realSendTime;
	}
	
}
