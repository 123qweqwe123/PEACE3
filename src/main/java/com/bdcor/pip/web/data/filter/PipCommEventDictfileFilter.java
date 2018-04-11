package com.bdcor.pip.web.data.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PipCommEventDictfileFilter extends BaseFilter {
	private String id;

	private String eventDictCode;

	private String eventName;

	private String fileInfo;

	private String remark;

	private String isUsed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventDictCode() {
		return eventDictCode;
	}

	public void setEventDictCode(String eventDictCode) {
		this.eventDictCode = eventDictCode;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
}