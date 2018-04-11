package com.bdcor.pip.web.progress.domain;

import java.util.Date;


public class MacStat {
	private String lccCode;
	private String lccName;
	private Date uploadDate;
	private String macName;
	
	public String getLccCode() {
		return lccCode;
	}
	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}
	public String getLccName() {
		return lccName;
	}
	public void setLccName(String lccName) {
		this.lccName = lccName;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getMacName() {
		return macName;
	}
	public void setMacName(String macName) {
		this.macName = macName;
	}
	
	

}
