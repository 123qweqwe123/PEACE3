package com.bdcor.pip.web.fee.domain;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


  


public class FeePlanVo {
	
	private String id;
	private String organId;
	private String projectId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date activat_time; 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date plan_complete_time;
	private Integer  amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reg_time;
	private String reg_person;
	private String useamount; 
	@Transient 
	private String activat_timeString;
	@Transient
	private String plan_complete_timeString;
	@Transient
	private String reg_timeString;
	 
	
	
	public FeePlanVo(){}
	
	public FeePlanVo(String id,String organId,String projectId,Date activat_time,Date plan_complete_time,
			Integer  amount,Date reg_time,String reg_person,String useamount ){
		 this.id=id;
		 this.organId=organId;
		 this.projectId=projectId;
		 this.activat_time=activat_time;
		 this.plan_complete_time=plan_complete_time;
		 this.amount= amount;
		 this.reg_time=reg_time;
		 this.reg_person=reg_person;
		 this.useamount=useamount;
	}  
	
	 
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUseamount() {
		return useamount;
	}

	public void setUseamount(String useamount) {
		this.useamount = useamount;
	}

	public String getActivat_timeString() {
		return activat_timeString;
	}


	public void setActivat_timeString(String activat_timeString) {
		this.activat_timeString = activat_timeString;
	}


	public String getPlan_complete_timeString() {
		return plan_complete_timeString;
	}


	public void setPlan_complete_timeString(String plan_complete_timeString) {
		this.plan_complete_timeString = plan_complete_timeString;
	}


	public String getReg_timeString() {
		return reg_timeString;
	}


	public void setReg_timeString(String reg_timeString) {
		this.reg_timeString = reg_timeString;
	}


	
	
	 
	public int getAmount() { 
		return amount;
	} 
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Date getActivat_time() {
		return activat_time;
	}
	public void setActivat_time(Date activat_time) {
		this.activat_time = activat_time;
	}
	public Date getPlan_complete_time() {
		return plan_complete_time;
	}
	public void setPlan_complete_time(Date plan_complete_time) {
		this.plan_complete_time = plan_complete_time;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	public String getId() {
		return id; 
	} 
	public void setId(String id) {
		this.id = id;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}

	
	
	public String getReg_person() {
		return reg_person;
	}
	public void setReg_person(String reg_person) {
		this.reg_person = reg_person;
	} 
}
