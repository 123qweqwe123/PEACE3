package com.bdcor.pip.web.data.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PatientFilter extends BaseFilter {
	
	
	private String linkMan1;
	private String linkMan1Mobile;
	private String linkMan1Relation;
	private String idNumber;
	private String belongGroup;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date firstEndDate ;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date firstStartDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sixEndDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sixStartDate;
	public void setBelongGroup(String belongGroup)
	{
		this.belongGroup = belongGroup;
	}
	public String getBelongGroup()
	{
		return belongGroup;
	}
	private String suifang; //随访
	
	private String suifangtype;//随访类型
	
	public String getLinkMan1()
	{
		return linkMan1;
	}
	public void setLinkMan1(String linkMan1)
	{
		this.linkMan1 = linkMan1;
	}
	public String getLinkMan1Mobile()
	{
		return linkMan1Mobile;
	}
	public void setLinkMan1Mobile(String linkMan1Mobile)
	{
		this.linkMan1Mobile = linkMan1Mobile;
	}
	public String getLinkMan1Relation()
	{
		return linkMan1Relation;
	}
	public void setLinkMan1Relation(String linkMan1Relation)
	{
		this.linkMan1Relation = linkMan1Relation;
	}
	public void setSuifang(String suifang)
	{
		this.suifang = suifang;
	}
	public String getSuifang()
	{
		return suifang;
	}
	public void setSuifangtype(String suifangtype)
	{
		this.suifangtype = suifangtype;
	}
	public String getSuifangtype()
	{
		return suifangtype;
	}
	public Date getFirstEndDate() {
		return firstEndDate;
	}

	public void setFirstEndDate(Date firstEndDate) {
		this.firstEndDate = firstEndDate;
	}

	public Date getFirstStartDate() {
		return firstStartDate;
	}

	public void setFirstStartDate(Date firstStartDate) {
		this.firstStartDate = firstStartDate;
	}

	public Date getSixEndDate() {
		return sixEndDate;
	}

	public void setSixEndDate(Date sixEndDate) {
		this.sixEndDate = sixEndDate;
	}

	public Date getSixStartDate() {
		return sixStartDate;
	}

	public void setSixStartDate(Date sixStartDate) {
		this.sixStartDate = sixStartDate;
	}

	private String projectId;

	private String patientId;

	private String patientName;

	private Date start;

	private Date end;

	private String fileName;

	private String lccCode;

	private String lccName;

	private String isRisk;// 是否高危

	private String isFollowview;// 是否预约随访

	private String type;// 1初筛 2高危

	private String insertToDbSuc;// excel文件是否导入到数据库中

	private String commPersonCode;

	private String createBy;

	private Date createDate;

	private String updateBy;

	private Date updateDate;

	private Short isRemoved;

	private String sex;

	private String nation;

	private Date birthday;

	private String nationality;

	private String credentialsType;

	private String credentialsCode;

	private String phone;

	private String mobile;

	private String email;

	private String maritalStatus;

	private String educational;

	private String postCode;

	private String nativeProvenceCode;

	private String nativeCityCode;

	private String nativeCountyCode;

	private String nativeTownCode;

	private String nativeVillageCode;

	private String nowProvenceCode;

	private String nowCityCode;

	private String nowCountyCode;

	private String nowTownCode;

	private String nowVillageCode;

	private String patientCode;

	private String bloodCode;

	private Date riskDate;

	private Date followviewDate;

	private String isFollowrisk;

	private Date followriskDate;

	private String riskCode;

	private Short isSpecial;
	
	private String userId;

	private  String isdiabetes;
	
	private Integer isJoinMsg;
	
	private String completeQn;
	
	private String noQn;
	
	private Integer isSystemDiabetes;
	
	private Integer isDead;

	private Integer is6State;

	private String processtypes;

	private String idlength;

	private String isNewAdd;

	public String getIsNewAdd() {
		return isNewAdd;
	}

	public void setIsNewAdd(String isNewAdd) {
		this.isNewAdd = isNewAdd;
	}

	public String getIdlength() {
		return idlength;
	}

	public void setIdlength(String idlength) {
		this.idlength = idlength;
	}

	public String getProcesstypes() {
		return processtypes;
	}

	public void setProcesstypes(String processtypes) {
		this.processtypes = processtypes;
	}

	public Integer getIs6State() {
		return is6State;
	}

	public void setIs6State(Integer is6State) {
		this.is6State = is6State;
	}

	public Integer getIsDead() {
		return isDead;
	}
	public void setIsDead(Integer isDead) {
		this.isDead = isDead;
	}
	public void setIsdiabetes(String isdiabetes) {
		this.isdiabetes = isdiabetes;
	}

	public String getIsdiabetes() {

		return isdiabetes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getLccName() {
		if ("".equals(lccName)) {
			return null;
		}
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getIsRisk() {
		if ("".equals(isRisk)) {
			return null;
		}
		return isRisk;
	}

	public void setIsRisk(String isRisk) {
		this.isRisk = isRisk;
	}

	public String getIsFollowview() {
		if ("".equals(isFollowview)) {
			return null;
		}
		return isFollowview;
	}

	public void setIsFollowview(String isFollowview) {
		this.isFollowview = isFollowview;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInsertToDbSuc() {
		return insertToDbSuc;
	}

	public void setInsertToDbSuc(String insertToDbSuc) {
		this.insertToDbSuc = insertToDbSuc;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getCommPersonCode() {
		return commPersonCode;
	}

	public void setCommPersonCode(String commPersonCode) {
		this.commPersonCode = commPersonCode;
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

	public Short getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Short isRemoved) {
		this.isRemoved = isRemoved;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getCredentialsCode() {
		return credentialsCode;
	}

	public void setCredentialsCode(String credentialsCode) {
		this.credentialsCode = credentialsCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getNativeProvenceCode() {
		return nativeProvenceCode;
	}

	public void setNativeProvenceCode(String nativeProvenceCode) {
		this.nativeProvenceCode = nativeProvenceCode;
	}

	public String getNativeCityCode() {
		return nativeCityCode;
	}

	public void setNativeCityCode(String nativeCityCode) {
		this.nativeCityCode = nativeCityCode;
	}

	public String getNativeCountyCode() {
		return nativeCountyCode;
	}

	public void setNativeCountyCode(String nativeCountyCode) {
		this.nativeCountyCode = nativeCountyCode;
	}

	public String getNativeTownCode() {
		return nativeTownCode;
	}

	public void setNativeTownCode(String nativeTownCode) {
		this.nativeTownCode = nativeTownCode;
	}

	public String getNativeVillageCode() {
		return nativeVillageCode;
	}

	public void setNativeVillageCode(String nativeVillageCode) {
		this.nativeVillageCode = nativeVillageCode;
	}

	public String getNowProvenceCode() {
		return nowProvenceCode;
	}

	public void setNowProvenceCode(String nowProvenceCode) {
		this.nowProvenceCode = nowProvenceCode;
	}

	public String getNowCityCode() {
		return nowCityCode;
	}

	public void setNowCityCode(String nowCityCode) {
		this.nowCityCode = nowCityCode;
	}

	public String getNowCountyCode() {
		return nowCountyCode;
	}

	public void setNowCountyCode(String nowCountyCode) {
		this.nowCountyCode = nowCountyCode;
	}

	public String getNowTownCode() {
		return nowTownCode;
	}

	public void setNowTownCode(String nowTownCode) {
		this.nowTownCode = nowTownCode;
	}

	public String getNowVillageCode() {
		return nowVillageCode;
	}

	public void setNowVillageCode(String nowVillageCode) {
		this.nowVillageCode = nowVillageCode;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	public String getBloodCode() {
		return bloodCode;
	}

	public void setBloodCode(String bloodCode) {
		this.bloodCode = bloodCode;
	}

	public Date getRiskDate() {
		return riskDate;
	}

	public void setRiskDate(Date riskDate) {
		this.riskDate = riskDate;
	}

	public Date getFollowviewDate() {
		return followviewDate;
	}

	public void setFollowviewDate(Date followviewDate) {
		this.followviewDate = followviewDate;
	}

	public String getIsFollowrisk() {
		return isFollowrisk;
	}

	public void setIsFollowrisk(String isFollowrisk) {
		this.isFollowrisk = isFollowrisk;
	}

	public Date getFollowriskDate() {
		return followriskDate;
	}

	public void setFollowriskDate(Date followriskDate) {
		this.followriskDate = followriskDate;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public Short getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Short isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getIsJoinMsg() {
		return isJoinMsg;
	}
	public void setIsJoinMsg(Integer isJoinMsg) {
		this.isJoinMsg = isJoinMsg;
	}
	
	public String getNoQn() {
		return noQn;
	}
	public void setNoQn(String noQn) {
		this.noQn = noQn;
	}
	public Integer getIsSystemDiabetes() {
		return isSystemDiabetes;
	}
	public void setIsSystemDiabetes(Integer isSystemDiabetes) {
		this.isSystemDiabetes = isSystemDiabetes;
	}
	public String getCompleteQn() {
		return completeQn;
	}
	public void setCompleteQn(String completeQn) {
		this.completeQn = completeQn;
	}
	
}
