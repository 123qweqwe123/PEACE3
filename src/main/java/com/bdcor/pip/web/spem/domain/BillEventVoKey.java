package com.bdcor.pip.web.spem.domain;

import java.io.Serializable;

public class BillEventVoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 事件
     */
    private String eventCode;

    /**
     * 项目编码
     */
    private String projectId;

    /**
     * 运送单
     */
    private String waybillNo; 

    /**
     * @return 事件
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * @param eventCode 
	 *            事件
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    } 

    /**
     * @return 项目编码
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId 
	 *            项目编码
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * @return 运送单
     */
    public String getWaybillNo() {
        return waybillNo;
    }

    /**
     * @param waybillNo 
	 *            运送单
     */
    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }
}