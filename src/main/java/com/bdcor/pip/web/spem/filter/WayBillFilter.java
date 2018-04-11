package com.bdcor.pip.web.spem.filter;

import java.util.HashMap;
import java.util.Map;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

/**
 * 公用条件查询类
 */
public class WayBillFilter extends BaseFilter{
	
	private String projectId; //项目ID
	
	private String lccid; //lccID
	
	private String waybill_no; //lccID
	
	private String boxCodes;
	
	private String state; 

	public String getProjectId() {
		return projectId;
	} 

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getLccid() {
		return lccid;
	}

	public void setLccid(String lccid) {
		this.lccid = lccid;
	}  


	public String getWaybill_no() {
		return waybill_no;
	}

	public void setWaybill_no(String waybill_no) {
		this.waybill_no = waybill_no;
	}

	public String getBoxCodes() {
		return boxCodes;
	}

	public void setBoxCodes(String boxCodes) {
		this.boxCodes = boxCodes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}	
    
}