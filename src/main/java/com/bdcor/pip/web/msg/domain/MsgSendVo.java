package com.bdcor.pip.web.msg.domain;

import java.util.Date;

public class MsgSendVo {
	
	private String id;
	
	private String lccId;
	
	private String patientId;
	
	private String patientName;
	
	private String idNumber;
	
	private String mobile;
	
	private Integer bGroup;
	
	private String msgName;
	
	private Date preSendTime;
	
	private Date realSendTime;
	
	private Integer state;
	
	private Integer isDelete;
	
	private String creatorName;
	
	private String createTime;
	
	private String typeCode;
	
	private String msgId;
	
	private String lccName;
	
	private Integer isNeedReply;
	private String sendCount;
	private String sendResult;
	private String sendReason;
	public String getSendCount()
	{
		return sendCount;
	}
	public String getSendReason()
	{
		return sendReason;
	}
	public String getSendResult()
	{
		return sendResult;
	}
	public void setSendCount(String sendCount)
	{
		this.sendCount = sendCount;
	}
	public void setSendReason(String sendReason)
	{
		this.sendReason = sendReason;
	}
	public void setSendResult(String sendResult)
	{
		this.sendResult = sendResult;
	}
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

	public Date getPreSendTime() {
		return preSendTime;
	}

	public void setPreSendTime(Date preSendTime) {
		this.preSendTime = preSendTime;
	}

	public Date getRealSendTime() {
		return realSendTime;
	}

	public void setRealSendTime(Date realSendTime) {
		this.realSendTime = realSendTime;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public Integer getIsNeedReply() {
		return isNeedReply;
	}

	public void setIsNeedReply(Integer isNeedReply) {
		this.isNeedReply = isNeedReply;
	}
	
}
