package com.bdcor.pip.web.pro.promgt.domain;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Lcc {

	private String projectId;
	private String lccCode; // 协作单位ID
	private String lccName; // 单位名称
	private String countryCode;
	private String areaCode; // 所属省份
	@Transient
	private String areaName;
	private Integer status; // 单位状态
	private String createBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	private Integer isRemoved;
	private String ftpIp;
	private Integer ftpPort;
	private String ftpUser;
	private String ftpPassword;
	private Integer costSum;
	private String englishName;
	private String address; // 地址
	private String projectManager;
	private String pmTel;
	private String pmEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contractSignDate; // 合同签署时间
	private String isSignContract; // 合同是否签署
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date higherApproveDate; // 批件时间
	private String isTraining; // 是中经过培训
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expectStartDate; // 目标启动时间
	private String lccType;
	private String isEthical; // 伦理是否通过
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ethicalEffectiveDate; // 伦理通过日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ethicalDisableDate; // 伦理批件到期日期
	private String ethicalPaperCode; // 伦理批件代码
	private String deliveryAddress;
	private String deliveryContract;
	private String deliveryTel;
	private String startDate;
	private String contact; // 联系人
	private String tel; // 联系人电话
	private String rccCode; // 所属协作单位
	private String parentName;
	private String isAdminDep; // 是否行政部门
	private String organ; // 组织机构
	private String lccRole; // 协作单位角色
	private String helpCode; // 拼音简码
	private String lccRoleType;// 单位分类
	@Transient
	private String projectName;
	@Transient
	private String contractSignDateStr;
	@Transient
	private String higherApproveDateStr;
	@Transient
	private String ethicalEffectiveDateStr;
	@Transient
	private String ethicalDisableDateStr;
	@Transient
	private String expectStartDateStr;
	@Transient
	private String organName;

	public Lcc() {
		super();
	}

	public String getLccRoleType() {
		return lccRoleType;
	}

	public void setLccRoleType(String lccRoleType) {
		this.lccRoleType = lccRoleType;
	}

	public Lcc(String projectId, String lccCode, String lccName,
			String countryCode, String areaCode, Integer status,
			String createBy, Date createDate, String updateBy, Date updateDate,
			Integer isRemoved, String ftpIp, Integer ftpPort, String ftpUser,
			String ftpPassword, Integer costSum, String englishName,
			String address, String projectManager, String pmTel,
			String pmEmail, Date contractSignDate, String isSignContract,
			Date higherApproveDate, String isTraining, Date expectStartDate,
			String lccType, String isEthical, Date ethicalEffectiveDate,
			Date ethicalDisableDate, String ethicalPaperCode,
			String deliveryAddress, String deliveryContract,
			String deliveryTel, String startDate, String contact, String tel,
			String rccCode, String isAdminDep, String organ, String lccRole,
			String helpCode) {
		super();
		this.projectId = projectId;
		this.lccCode = lccCode;
		this.lccName = lccName;
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.isRemoved = isRemoved;
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.ftpUser = ftpUser;
		this.ftpPassword = ftpPassword;
		this.costSum = costSum;
		this.englishName = englishName;
		this.address = address;
		this.projectManager = projectManager;
		this.pmTel = pmTel;
		this.pmEmail = pmEmail;
		this.contractSignDate = contractSignDate;
		this.isSignContract = isSignContract;
		this.higherApproveDate = higherApproveDate;
		this.isTraining = isTraining;
		this.expectStartDate = expectStartDate;
		this.lccType = lccType;
		this.isEthical = isEthical;
		this.ethicalEffectiveDate = ethicalEffectiveDate;
		this.ethicalDisableDate = ethicalDisableDate;
		this.ethicalPaperCode = ethicalPaperCode;
		this.deliveryAddress = deliveryAddress;
		this.deliveryContract = deliveryContract;
		this.deliveryTel = deliveryTel;
		this.startDate = startDate;
		this.contact = contact;
		this.tel = tel;
		this.rccCode = rccCode;
		this.isAdminDep = isAdminDep;
		this.organ = organ;
		this.lccRole = lccRole;
		this.helpCode = helpCode;
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

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Integer isRemoved) {
		this.isRemoved = isRemoved;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public Integer getCostSum() {
		return costSum;
	}

	public void setCostSum(Integer costSum) {
		this.costSum = costSum;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getPmTel() {
		return pmTel;
	}

	public void setPmTel(String pmTel) {
		this.pmTel = pmTel;
	}

	public String getPmEmail() {
		return pmEmail;
	}

	public void setPmEmail(String pmEmail) {
		this.pmEmail = pmEmail;
	}

	public Date getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public String getIsSignContract() {
		return isSignContract;
	}

	public void setIsSignContract(String isSignContract) {
		this.isSignContract = isSignContract;
	}

	public Date getHigherApproveDate() {
		return higherApproveDate;
	}

	public void setHigherApproveDate(Date higherApproveDate) {
		this.higherApproveDate = higherApproveDate;
	}

	public String getIsTraining() {
		return isTraining;
	}

	public void setIsTraining(String isTraining) {
		this.isTraining = isTraining;
	}

	public Date getExpectStartDate() {
		return expectStartDate;
	}

	public void setExpectStartDate(Date expectStartDate) {
		this.expectStartDate = expectStartDate;
	}

	public String getLccType() {
		return lccType;
	}

	public void setLccType(String lccType) {
		this.lccType = lccType;
	}

	public String getIsEthical() {
		return isEthical;
	}

	public void setIsEthical(String isEthical) {
		this.isEthical = isEthical;
	}

	public Date getEthicalEffectiveDate() {
		return ethicalEffectiveDate;
	}

	public void setEthicalEffectiveDate(Date ethicalEffectiveDate) {
		this.ethicalEffectiveDate = ethicalEffectiveDate;
	}

	public Date getEthicalDisableDate() {
		return ethicalDisableDate;
	}

	public void setEthicalDisableDate(Date ethicalDisableDate) {
		this.ethicalDisableDate = ethicalDisableDate;
	}

	public String getEthicalPaperCode() {
		return ethicalPaperCode;
	}

	public void setEthicalPaperCode(String ethicalPaperCode) {
		this.ethicalPaperCode = ethicalPaperCode;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryContract() {
		return deliveryContract;
	}

	public void setDeliveryContract(String deliveryContract) {
		this.deliveryContract = deliveryContract;
	}

	public String getDeliveryTel() {
		return deliveryTel;
	}

	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIsAdminDep() {
		return isAdminDep;
	}

	public void setIsAdminDep(String isAdminDep) {
		this.isAdminDep = isAdminDep;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getLccRole() {
		return lccRole;
	}

	public void setLccRole(String lccRole) {
		this.lccRole = lccRole;
	}

	public String getContractSignDateStr() {
		return contractSignDateStr;
	}

	public void setContractSignDateStr(String contractSignDateStr) {
		this.contractSignDateStr = contractSignDateStr;
	}

	public String getHigherApproveDateStr() {
		return higherApproveDateStr;
	}

	public void setHigherApproveDateStr(String higherApproveDateStr) {
		this.higherApproveDateStr = higherApproveDateStr;
	}

	public String getEthicalEffectiveDateStr() {
		return ethicalEffectiveDateStr;
	}

	public void setEthicalEffectiveDateStr(String ethicalEffectiveDateStr) {
		this.ethicalEffectiveDateStr = ethicalEffectiveDateStr;
	}

	public String getEthicalDisableDateStr() {
		return ethicalDisableDateStr;
	}

	public void setEthicalDisableDateStr(String ethicalDisableDateStr) {
		this.ethicalDisableDateStr = ethicalDisableDateStr;
	}

	public String getExpectStartDateStr() {
		return expectStartDateStr;
	}

	public void setExpectStartDateStr(String expectStartDateStr) {
		this.expectStartDateStr = expectStartDateStr;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public String getRccCode() {
		return rccCode;
	}

	public void setRccCode(String rccCode) {
		this.rccCode = rccCode;
	}

}
