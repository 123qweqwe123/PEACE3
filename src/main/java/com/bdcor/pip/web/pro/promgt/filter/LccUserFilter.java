package com.bdcor.pip.web.pro.promgt.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class LccUserFilter extends BaseFilter {

	private String name;
	private String projectId;
	private String lccCode;
	private String userId;

	// peace 3 新修改内容
	private String vName; // 地区名称
	private String lccName; // lcc名称
	private String hospitalType; // 医院类型
	private String isSignContract; // 是否签约
	private String contractSignDate;// 签约时间
	private String expectStartDate;// 启动时间
	private String projectManager;// 项目负责人
	private String rccName; // 所属单位
	private String lccRole; // 角色
	private String researchStatus;// 参严1 离严0
	private String lccRoleType;// --单位分类

	public void setLccRoleType(String lccRoleType) {
		this.lccRoleType = lccRoleType;
	}

	public String getLccRoleType() {
		return lccRoleType;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getIsSignContract() {
		return isSignContract;
	}

	public void setIsSignContract(String isSignContract) {
		this.isSignContract = isSignContract;
	}

	public String getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(String contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public String getExpectStartDate() {
		return expectStartDate;
	}

	public void setExpectStartDate(String expectStartDate) {
		this.expectStartDate = expectStartDate;
	}

	public String getRccName() {
		return rccName;
	}

	public void setRccName(String rccName) {
		this.rccName = rccName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getResearchStatus() {
		return researchStatus;
	}

	public void setResearchStatus(String researchStatus) {
		this.researchStatus = researchStatus;
	}

	public String getLccRole() {
		return lccRole;
	}

	public void setLccRole(String lccRole) {
		this.lccRole = lccRole;
	}

}
