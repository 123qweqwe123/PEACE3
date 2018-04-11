package com.bdcor.pip.web.qn.domain;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Description:
 * Author: huangrupeng
 * Create: 17/3/2 下午1:52
 */
public class PipCommEventVO1 extends PipCommEvent1 {

    private String currentdescStr; // 评审后事件描述
    private String eventendingStr; // 事件结局
    private String reportstatusStr; // 评审状态
    private String lccName; // 协作医院
    private boolean addingEvent;  // 是否是新增事件 true:是 false:不是
    private String srcdescStr;  // 评审前事件描述

    public String getCurrentdescStr() {
        return currentdescStr;
    }

    public void setCurrentdescStr(String currentdescStr) {
        this.currentdescStr = currentdescStr;
    }

    public String getEventendingStr() {
        return eventendingStr;
    }

    public void setEventendingStr(String eventendingStr) {
        this.eventendingStr = eventendingStr;
    }

    public String getReportstatusStr() {
        return reportstatusStr;
    }

    public void setReportstatusStr(String reportstatusStr) {
        this.reportstatusStr = reportstatusStr;
    }

    public String getLccName() {
        return lccName;
    }

    public void setLccName(String lccName) {
        this.lccName = lccName;
    }

    public boolean isAddingEvent() {
        return !NumberUtils.isNumber(getEventCode());
    }

    public void setAddingEvent(boolean addingEvent) {
        this.addingEvent = addingEvent;
    }

    public String getSrcdescStr() {
        return srcdescStr;
    }

    public void setSrcdescStr(String srcdescStr) {
        this.srcdescStr = srcdescStr;
    }
}
