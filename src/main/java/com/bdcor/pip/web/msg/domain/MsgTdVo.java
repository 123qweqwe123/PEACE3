package com.bdcor.pip.web.msg.domain;

import java.util.Date;

public class MsgTdVo {
	
	private String id;
	
	private String lccId;
	
	private String patientId;
	
	private String patientName;
	
	private String idNumber;
	
	private String mobile;
	
	private Integer bGroup;
	
	private String msgName;
	
	private Date replyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLccId() {
		return lccId;
	}

	public void setLccId(String lccId) {
		this.lccId = lccId;
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

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getbGroup() {
		return bGroup;
	}

	public void setbGroup(Integer bGroup) {
		this.bGroup = bGroup;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	
}
