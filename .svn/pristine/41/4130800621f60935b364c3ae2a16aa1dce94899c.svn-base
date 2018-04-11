package com.bdcor.pip.web.fee.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class FeeRegister {
	private String id; // id
	private String projectId;// 项目id
	private String projectName;// 项目id
	private String reg_type;//  登记类型
	//private String reg_type_name;//  登记类型
	private String departId;//  部门id 
	private String departName;//  部门id 
	private Integer amount;//    金额  
	private String reasons; //  登记事由 
	private Date uTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reg_date; //  登记事由  REGISTRANT  
	@Transient
	private String reg_dateString; //  登记事由  REGISTRANT
	
	
	
	
	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public Date getReg_date() {  
		return reg_date;
	}  
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	private String registrant; //登记人  
	
	private String tally_month; //财务月份
	
	private String tally_year; //财务年份   
	
	   
	public String getReg_dateString() {
		return reg_dateString;
	}
	public void setReg_dateString(String reg_dateString) {
		this.reg_dateString = reg_dateString;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		
		SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM-dd");
		this.reg_dateString=format.format(reg_date);
	}
	public String getRegistrant() { 
		return registrant;
	}
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	public String getTally_month() {
		return tally_month;
	}
	public void setTally_month(String tally_month) {
		this.tally_month = tally_month;
	}
	public String getTally_year() {
		return tally_year;
	}
	public void setTally_year(String tally_year) {
		this.tally_year = tally_year;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String feeId) {
		this.id = feeId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getReg_type() {
		return reg_type;
	}
	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
		
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
}
