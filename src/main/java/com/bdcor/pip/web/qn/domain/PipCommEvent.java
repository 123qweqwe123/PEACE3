package com.bdcor.pip.web.qn.domain;

import java.util.Date;

public class PipCommEvent {
    private String lccCode;

    private String patientId;

    private String eventCode;

    private String patientName;

    private String eventName;

    private Date inHosDate;

    private Date outHosDate;

    private String hosName;

    private Date eventDate;

    private String questionnaireId;

    private String projectId;

    private String isDeath;

    private String eventType;

    private Short isDelete;

    private String logMinId;

    private String uqsNo;

    private String createBy;

    private Date createDate;

    private Short checkStatus;

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

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getInHosDate() {
        return inHosDate;
    }

    public void setInHosDate(Date inHosDate) {
        this.inHosDate = inHosDate;
    }

    public Date getOutHosDate() {
        return outHosDate;
    }

    public void setOutHosDate(Date outHosDate) {
        this.outHosDate = outHosDate;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getIsDeath() {
        return isDeath;
    }

    public void setIsDeath(String isDeath) {
        this.isDeath = isDeath;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public String getLogMinId() {
        return logMinId;
    }

    public void setLogMinId(String logMinId) {
        this.logMinId = logMinId;
    }

    public String getUqsNo() {
        return uqsNo;
    }

    public void setUqsNo(String uqsNo) {
        this.uqsNo = uqsNo;
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

    public Short getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Short checkStatus) {
        this.checkStatus = checkStatus;
    }
}