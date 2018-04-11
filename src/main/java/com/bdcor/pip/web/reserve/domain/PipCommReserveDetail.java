package com.bdcor.pip.web.reserve.domain;

import java.util.Date;

public class PipCommReserveDetail {

	 private  int id; 
	 private  Date docTime;  //医生预约时间
     private  String  patientId; //患者的ID号
     private  String  projectId; //项目号
	 private  Date vTime;     //患者计划到达时间
	 private  Date planTime;     //患者预约到达时间
	 private  Date realTime;     //患者检查时间
	 private String changeComeDate; //患者修改来院时间
	 private  String version;  //默认是 1
	 private  String doc ;     //医生名称
	 private  String docNo ;   //医生编号
	 private  String  resultCode ;   //预约结果编号  RESULT_CODE
	 private  String  result ; //预约结果
	 private  String  remark ; //备注
     
	 
     public PipCommReserveDetail(){}
     
     

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Date getDocTime() {
		return docTime;
	}



	public void setDocTime(Date docTime) {
		this.docTime = docTime;
	}



	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	
	public Date getRealTime() {
		return realTime;
	}



	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}



	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getPatientId() {
		return patientId;
	}



	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}



	public String getProjectId() {
		return projectId;
	}



	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}



	public String getChangeComeDate() {
		return changeComeDate;
	}



	public void setChangeComeDate(String changeComeDate) {
		this.changeComeDate = changeComeDate;
	}



	public String getResultCode() {
		return resultCode;
	}



	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}



	public Date getvTime() {
		return vTime;
	}



	public void setvTime(Date vTime) {
		this.vTime = vTime;
	}





	
}
