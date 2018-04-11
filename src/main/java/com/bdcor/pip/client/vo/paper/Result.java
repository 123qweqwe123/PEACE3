package com.bdcor.pip.client.vo.paper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.client.xml.model.result.ResultDoctypeDocumentBean.Doctype;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean.Questionnaire;
import com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean;
import com.bdcor.pip.core.utils.Securitys;
/**
 * 结果
 * @author Administrator
 *
 */
public class Result {
	private String title;
	private String projectID;
	private String projectName;
	private String UQSID;
	private String UQSName;
	private String UQSVersion;
	private String versionCreateDate;
	private String UQSBeginTime;
	private String UQSEndDate;
	private String UQSTimeZone;
	private String operaterID;
	private String operaterName;
	private String hospitalCode;
	private String hospitalName;
	private String patientID;
	private String personID;
	private String patientCode;
	private String patientName;
	private String UQSIsHold;
	private String UQSRemark;

	public Result(){}
	
	public Result(Questionnaire resultQuestion){
		this.title = resultQuestion.getTitle();
		
		Doctype docType = resultQuestion.getDoctype();
		this.projectID = docType.getProjectID();
		this.projectName = docType.getProjectName();
		this.UQSID = docType.getUQSID();
		this.UQSName = docType.getUQSName();
		this.UQSVersion = docType.getUQSVersion();
		this.versionCreateDate = docType.getVersionCreateDate();
		this.UQSBeginTime = docType.getUQSBeginTime();
		this.UQSEndDate = docType.getUQSEndDate();
		this.UQSTimeZone = docType.getUQSTimeZone();
		this.operaterID = docType.getOperaterID();
		this.operaterName = docType.getOperaterName();
		this.hospitalCode = docType.getHospitalCode();
		this.hospitalName = docType.getHospitalName();
		this.patientID = docType.getPatientID();
		this.personID = docType.getPersonID();
		this.patientName = docType.getPatientName();
		this.patientCode = docType.getPatientCode();
		this.UQSIsHold = docType.getUQSIsHold();
		this.UQSRemark = docType.getUQSRemark();
		for(ResultResultDocumentBean.Result r:resultQuestion.getResultArray()){
			this.options.add(new ResultOption(r));
		}
	}
	
	public Result(Paper paper){
		this.projectID = paper.getProjectId();
		this.projectName = paper.getProjectName();
		this.UQSID = paper.getPaperId();
		this.UQSName = paper.getPaperName();
		this.UQSVersion = paper.getPaperVersion();
		this.versionCreateDate = paper.getCreateDate();
//		this.patientCode = interviewee.getCpIntervCode();
//		this.UQSBeginTime;
//		this.UQSEndDate;
//		this.UQSTimeZone;
//		this.operaterID;
//		this.operaterName;
//		this.hospitalCode;
//		this.hospitalName;
//		this.patientID;
//		this.personID;
//		this.patientName;
	}

	public Result(HttpServletRequest request,Paper paper) {
		this.projectID = paper.getProjectId();
		this.projectName = paper.getProjectName();
		this.UQSID = paper.getPaperId();
		this.UQSName = paper.getPaperName();
		this.UQSVersion = paper.getPaperVersion();
		this.versionCreateDate = paper.getCreateDate();
		
		this.operaterID = Securitys.getUserId();
		this.patientID = request.getParameter("patientId");
		this.operaterName = Securitys.getLoginName();
		this.UQSBeginTime = request.getParameter("UQSBeginTime");
		this.UQSRemark =  request.getParameter("UQSRemark");
		this.UQSEndDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		
		@SuppressWarnings("rawtypes")
		Enumeration enumer = request.getParameterNames();
		while(enumer.hasMoreElements()){
			String questinId = enumer.nextElement().toString();
			if(questinId.startsWith("OPTION")){
				String anserId = request.getParameter(questinId);
				ResultOption opton = new ResultOption(questinId,anserId);
				options.add(opton);
			}
		}
	}

	private List<ResultOption> options = new ArrayList<ResultOption>();
	public List<ResultOption> getOptions() {
		return options;
	}
	public void setOptions(List<ResultOption> options) {
		this.options = options;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUQSID() {
		return UQSID;
	}
	public void setUQSID(String uQSID) {
		UQSID = uQSID;
	}
	public String getUQSName() {
		return UQSName;
	}
	public void setUQSName(String uQSName) {
		UQSName = uQSName;
	}
	public String getUQSVersion() {
		return UQSVersion;
	}
	public void setUQSVersion(String uQSVersion) {
		UQSVersion = uQSVersion;
	}
	public String getVersionCreateDate() {
		return versionCreateDate;
	}
	public void setVersionCreateDate(String versionCreateDate) {
		this.versionCreateDate = versionCreateDate;
	}
	public String getUQSBeginTime() {
		return UQSBeginTime;
	}
	public void setUQSBeginTime(String uQSBeginTime) {
		UQSBeginTime = uQSBeginTime;
	}
	public String getUQSEndDate() {
		return UQSEndDate;
	}
	public void setUQSEndDate(String uQSEndDate) {
		UQSEndDate = uQSEndDate;
	}
	public String getUQSTimeZone() {
		return UQSTimeZone;
	}
	public void setUQSTimeZone(String uQSTimeZone) {
		UQSTimeZone = uQSTimeZone;
	}
	public String getOperaterID() {
		return operaterID;
	}
	public void setOperaterID(String operaterID) {
		this.operaterID = operaterID;
	}
	public String getOperaterName() {
		return operaterName;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	public String getUQSIsHold() {
		return UQSIsHold;
	}

	public void setUQSIsHold(String uQSIsHold) {
		UQSIsHold = uQSIsHold;
	}

	public String getUQSRemark() {
		return UQSRemark;
	}

	public void setUQSRemark(String uQSRemark) {
		UQSRemark = uQSRemark;
	}
	
}
