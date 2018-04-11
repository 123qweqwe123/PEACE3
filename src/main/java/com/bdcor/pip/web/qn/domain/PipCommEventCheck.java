package com.bdcor.pip.web.qn.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PipCommEventCheck {
    private Short id;

    private String eventCode;

    private String pid;

    private String originDesc;

    private String currentDesc;

    private Short isDead;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date occurDate;

    private Short occurDateType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endingDate;

    private Short endingDateType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadDate;

    private Short deadDateType;

    private Short eventPlace;

    private String hospitalName;

    private Short inHospitalDay;

    private String reportRemark;

    private String remark;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String originEventEnding;

    private Short eventEnding;

    private Short reportStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reportDate;

    private String reportBy;

    private String currentRemark;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date originDeadDate;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOriginDesc() {
        return originDesc;
    }

    public void setOriginDesc(String originDesc) {
        this.originDesc = originDesc;
    }

    public String getCurrentDesc() {
        return currentDesc;
    }

    public void setCurrentDesc(String currentDesc) {
        this.currentDesc = currentDesc;
    }

    public Short getIsDead() {
        return isDead;
    }

    public void setIsDead(Short isDead) {
        this.isDead = isDead;
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public Short getOccurDateType() {
        return occurDateType;
    }

    public void setOccurDateType(Short occurDateType) {
        this.occurDateType = occurDateType;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Short getEndingDateType() {
        return endingDateType;
    }

    public void setEndingDateType(Short endingDateType) {
        this.endingDateType = endingDateType;
    }

    public Date getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }

    public Short getDeadDateType() {
        return deadDateType;
    }

    public void setDeadDateType(Short deadDateType) {
        this.deadDateType = deadDateType;
    }

    public Short getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(Short eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Short getInHospitalDay() {
        return inHospitalDay;
    }

    public void setInHospitalDay(Short inHospitalDay) {
        this.inHospitalDay = inHospitalDay;
    }

    public String getReportRemark() {
        return reportRemark;
    }

    public void setReportRemark(String reportRemark) {
        this.reportRemark = reportRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getOriginEventEnding() {
        return originEventEnding;
    }

    public void setOriginEventEnding(String originEventEnding) {
        this.originEventEnding = originEventEnding;
    }

    public Short getEventEnding() {
        return eventEnding;
    }

    public void setEventEnding(Short eventEnding) {
        this.eventEnding = eventEnding;
    }

    public Short getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Short reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportBy() {
        return reportBy;
    }

    public void setReportBy(String reportBy) {
        this.reportBy = reportBy;
    }

    public String getCurrentRemark() {
        return currentRemark;
    }

    public void setCurrentRemark(String currentRemark) {
        this.currentRemark = currentRemark;
    }

    public Date getOriginDeadDate() {
        return originDeadDate;
    }

    public void setOriginDeadDate(Date originDeadDate) {
        this.originDeadDate = originDeadDate;
    }
}