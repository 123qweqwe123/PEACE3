package com.bdcor.pip.web.qn.domain;

import java.util.Date;

public class PipUqsAnswerqnLog {
    private String projectId;

    private String lccCode;

    private String patientId;

    private String uqsVersion;

    private Date startTime;

    private Date endTime;

    private String createBy;

    private String uqsFile;

    private Integer state;

    private Date createDate;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getUqsVersion() {
        return uqsVersion;
    }

    public void setUqsVersion(String uqsVersion) {
        this.uqsVersion = uqsVersion;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUqsFile() {
        return uqsFile;
    }

    public void setUqsFile(String uqsFile) {
        this.uqsFile = uqsFile;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}