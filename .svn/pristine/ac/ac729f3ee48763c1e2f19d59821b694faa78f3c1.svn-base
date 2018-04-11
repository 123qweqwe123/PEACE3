package com.bdcor.pip.web.qn.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:
 * Author: huangrupeng
 * Create: 17/3/2 下午1:56
 */
public class EventCheckFilter extends BaseFilter {

    // 事件发生事件
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inHosDateBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inHosDateEnd;

    // 事件报告起始、结束事件
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDateBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDateEnd;

    // PID
    private String patientId;
    // 事件ID
    private String eventCode;
    private String lccCode;
    // 事件结局
    private String eventending;
    // 评审状态
    private String reportstatus;

    private int showAddEvents = 0;//默认不显示新增事件

    private String isEnding;// 是否终点事件


    public Date getInHosDateBegin() {
        return inHosDateBegin;
    }

    public void setInHosDateBegin(Date inHosDateBegin) {
        this.inHosDateBegin = inHosDateBegin;
    }

    public Date getInHosDateEnd() {
        return inHosDateEnd;
    }

    public void setInHosDateEnd(Date inHosDateEnd) {
        this.inHosDateEnd = inHosDateEnd;
    }

    public Date getEventDateBegin() {
        return eventDateBegin;
    }

    public void setEventDateBegin(Date eventDateBegin) {
        this.eventDateBegin = eventDateBegin;
    }

    public Date getEventDateEnd() {
        if(eventDateEnd != null) {
            return new DateTime(eventDateEnd).plusDays(1).toDate();
        }
        return null;
    }

    public void setEventDateEnd(Date eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
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

    public String getLccCode() {
        return lccCode;
    }

    public void setLccCode(String lccCode) {
        this.lccCode = lccCode;
    }

    public String getEventending() {
        return eventending;
    }

    public void setEventending(String eventending) {
        this.eventending = eventending;
    }

    public String getReportstatus() {
        return reportstatus;
    }

    public void setReportstatus(String reportstatus) {
        this.reportstatus = reportstatus;
    }

    public int getShowAddEvents() {
        return showAddEvents;
    }

    public void setShowAddEvents(int showAddEvents) {
        this.showAddEvents = showAddEvents;
    }

    public String getIsEnding() {
        return isEnding;
    }

    public void setIsEnding(String isEnding) {
        this.isEnding = isEnding;
    }
}
