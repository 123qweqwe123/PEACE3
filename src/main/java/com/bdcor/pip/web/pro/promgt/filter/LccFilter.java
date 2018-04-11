package com.bdcor.pip.web.pro.promgt.filter;

import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class LccFilter extends BaseFilter {

	private String lccCode;
	private String areaCode;
	private String projectId;
	private String userId;

	// peace 3 新修改内容
	private String areaName; // 地区名称
	private String lccName; // lcc名称
	private String hospitalType; // 医院类型
	private String isSignContract; // 是否签约
	private Date contractSignDate;// 签约时间
	private Date expectStartDate;// 启动时间
	private String projectManager;// 项目负责人
	private String rccName; // 所属单位
	private String isEthical; // 是否通过伦理
	private String totalCount; // 总人数
	private String status; // 激活状态
	private String rccCode; // --所属单位code
	private String lccRoleType; // -- 医院类型
	public void setLccRoleType(String lccRoleType)
	{
		this.lccRoleType = lccRoleType;
	}
	public String getLccRoleType()
	{
		return lccRoleType;
	}
	public void setRccCode(String rccCode) {
		this.rccCode = rccCode;
	}

	public String getRccCode() {
		return rccCode;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setIsEthical(String isEthical) {
		this.isEthical = isEthical;
	}

	public String getIsEthical() {
		return isEthical;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Date getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public Date getExpectStartDate() {
		return expectStartDate;
	}

	public void setExpectStartDate(Date expectStartDate) {
		this.expectStartDate = expectStartDate;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getRccName() {
		return rccName;
	}

	public void setRccName(String rccName) {
		this.rccName = rccName;
	}

}
