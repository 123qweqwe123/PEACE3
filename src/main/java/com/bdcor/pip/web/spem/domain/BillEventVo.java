package com.bdcor.pip.web.spem.domain;

import java.io.Serializable;
import java.util.Date;

public class BillEventVo extends BillEventVoKey implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 事件发生日期
     */
    private Date eventDate;

    /**
     * 责任人
     */
    private String liableSb;
    
    /**
     * 责任人
     */
    private String liableSbName;
    
    /**
     * 事件名称
     */
    private String event_name;
    

    	
     
    public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getLiableSbName() {
		return liableSbName;
	}

	public void setLiableSbName(String liableSbName) {
		this.liableSbName = liableSbName;
	}

	private Date createDate;

    private String createBy;

    /**
     * @return 事件发生日期
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate 
	 *            事件发生日期
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return 责任人
     */
    public String getLiableSb() {
        return liableSb;
    }

    /**
     * @param liableSb 
	 *            责任人
     */
    public void setLiableSb(String liableSb) {
        this.liableSb = liableSb == null ? null : liableSb.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}