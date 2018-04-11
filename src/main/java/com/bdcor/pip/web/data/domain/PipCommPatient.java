package com.bdcor.pip.web.data.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PipCommPatient extends PipCommPatientKey {

	private String patientName;

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

	private String idNumber;

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

	private String lccCode;

	private String lccName;

	private String patientCode;

	private String bloodCode;

	private String isRisk;

	private Date riskDate;

	private String isFollowview;
	private String belongGroup;
	private Date groupDate;
	private String isSmoking;
	private String isSubscribe;
	private String isHypertension;
	private String isJoinMsg;
	private Integer isDiabetes;

	private String isXjgsbs;

	private Integer state010;
	private Integer state011;
	private Integer state012;
	private Integer state002002;
	private Integer state015;
	private Integer betweenDays;

	private Integer sendType;

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public Integer getBetweenDays() {
		return betweenDays;
	}

	public void setBetweenDays(Integer betweenDays) {
		this.betweenDays = betweenDays;
	}

	public Integer getState015() {
		return state015;
	}

	public void setState015(Integer state015) {
		this.state015 = state015;
	}

	public Integer getState014() {
		return state014;
	}

	public void setState014(Integer state014) {
		this.state014 = state014;
	}

	private Integer state014;
	private Integer isSystemDiabetes;

	private Integer is6State;
	private Integer isDead;

	public Integer getIsDead() {
		return isDead;
	}

	public void setIsDead(Integer isDead) {
		this.isDead = isDead;
	}

	public Integer getIs6State() {
		return is6State;
	}

	public void setIs6State(Integer is6State) {
		this.is6State = is6State;
	}

	public void setIsXjgsbs(String isXjgsbs) {
		this.isXjgsbs = isXjgsbs;
	}

	public String getIsXjgsbs() {
		return isXjgsbs;
	}

	public String getBelongGroup() {
		return belongGroup;
	}

	public void setBelongGroup(String belongGroup) {
		this.belongGroup = belongGroup;
	}

	public Date getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}

	public String getIsSmoking() {
		return isSmoking;
	}

	public void setIsSmoking(String isSmoking) {
		this.isSmoking = isSmoking;
	}

	public void setIsJoinMsg(String isJoinMsg) {
		this.isJoinMsg = isJoinMsg;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getIsHypertension() {
		return isHypertension;
	}

	public void setIsHypertension(String isHypertension) {
		this.isHypertension = isHypertension;
	}
	
	public String getIsJoinMsg() {
		return isJoinMsg;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date followviewDate;
	private String followviewDateStr;
	private String linkMan1;
	private String linkMan1Relation;
	private String linkMan1Mobile;
	private String linkMan2;
	private String linkMan2Relation;
	private String linkMan2Mobile;
	private String caseCode;
	private String linkMan1Phone;
	private String linkMan2Phone;
	private String lastFollowviewDate;
	
	private String isFollowrisk;
	private Date followriskDate;
	private String riskCode;
	private Short isSpecial;

	public String getLinkMan1() {
		return linkMan1;
	}

	public void setLinkMan1(String linkMan1) {
		this.linkMan1 = linkMan1;
	}

	public String getLinkMan1Relation() {
		return linkMan1Relation;
	}

	public void setLinkMan1Relation(String linkMan1Relation) {
		this.linkMan1Relation = linkMan1Relation;
	}

	public String getLinkMan1Mobile() {
		return linkMan1Mobile;
	}

	public void setLinkMan1Mobile(String linkMan1Mobile) {
		this.linkMan1Mobile = linkMan1Mobile;
	}

	public String getLinkMan2() {
		return linkMan2;
	}

	public void setLinkMan2(String linkMan2) {
		this.linkMan2 = linkMan2;
	}

	public String getLinkMan2Relation() {
		return linkMan2Relation;
	}

	public void setLinkMan2Relation(String linkMan2Relation) {
		this.linkMan2Relation = linkMan2Relation;
	}

	public String getLinkMan2Mobile() {
		return linkMan2Mobile;
	}

	public void setLinkMan2Mobile(String linkMan2Mobile) {
		this.linkMan2Mobile = linkMan2Mobile;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getLinkMan1Phone() {
		return linkMan1Phone;
	}

	public void setLinkMan1Phone(String linkMan1Phone) {
		this.linkMan1Phone = linkMan1Phone;
	}

	public String getLinkMan2Phone() {
		return linkMan2Phone;
	}

	public void setLinkMan2Phone(String linkMan2Phone) {
		this.linkMan2Phone = linkMan2Phone;
	}

	public String getLastFollowviewDate() {
		return lastFollowviewDate;
	}

	public void setLastFollowviewDate(String lastFollowviewDate) {
		this.lastFollowviewDate = lastFollowviewDate;
	}

	public void setFollowviewDateStr(String followviewDateStr) {
		this.followviewDateStr = followviewDateStr;
	}

	public String getFollowviewDateStr() {
		return followviewDateStr;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName == null ? null : patientName.trim();
	}

	public String getCommPersonCode() {
		return commPersonCode;
	}

	public void setCommPersonCode(String commPersonCode) {
		this.commPersonCode = commPersonCode == null ? null : commPersonCode
				.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
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
		this.updateBy = updateBy == null ? null : updateBy.trim();
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
		this.sex = sex == null ? null : sex.trim();
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
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
		this.nationality = nationality == null ? null : nationality.trim();
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber == null ? null : idNumber.trim();
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType == null ? null : credentialsType
				.trim();
	}

	public String getCredentialsCode() {
		return credentialsCode;
	}

	public void setCredentialsCode(String credentialsCode) {
		this.credentialsCode = credentialsCode == null ? null : credentialsCode
				.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus == null ? null : maritalStatus
				.trim();
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational == null ? null : educational.trim();
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode == null ? null : postCode.trim();
	}

	public String getNativeProvenceCode() {
		return nativeProvenceCode;
	}

	public void setNativeProvenceCode(String nativeProvenceCode) {
		this.nativeProvenceCode = nativeProvenceCode == null ? null
				: nativeProvenceCode.trim();
	}

	public String getNativeCityCode() {
		return nativeCityCode;
	}

	public void setNativeCityCode(String nativeCityCode) {
		this.nativeCityCode = nativeCityCode == null ? null : nativeCityCode
				.trim();
	}

	public String getNativeCountyCode() {
		return nativeCountyCode;
	}

	public void setNativeCountyCode(String nativeCountyCode) {
		this.nativeCountyCode = nativeCountyCode == null ? null
				: nativeCountyCode.trim();
	}

	public String getNativeTownCode() {
		return nativeTownCode;
	}

	public void setNativeTownCode(String nativeTownCode) {
		this.nativeTownCode = nativeTownCode == null ? null : nativeTownCode
				.trim();
	}

	public String getNativeVillageCode() {
		return nativeVillageCode;
	}

	public void setNativeVillageCode(String nativeVillageCode) {
		this.nativeVillageCode = nativeVillageCode == null ? null
				: nativeVillageCode.trim();
	}

	public String getNowProvenceCode() {
		return nowProvenceCode;
	}

	public void setNowProvenceCode(String nowProvenceCode) {
		this.nowProvenceCode = nowProvenceCode == null ? null : nowProvenceCode
				.trim();
	}

	public String getNowCityCode() {
		return nowCityCode;
	}

	public void setNowCityCode(String nowCityCode) {
		this.nowCityCode = nowCityCode == null ? null : nowCityCode.trim();
	}

	public String getNowCountyCode() {
		return nowCountyCode;
	}

	public void setNowCountyCode(String nowCountyCode) {
		this.nowCountyCode = nowCountyCode == null ? null : nowCountyCode
				.trim();
	}

	public String getNowTownCode() {
		return nowTownCode;
	}

	public void setNowTownCode(String nowTownCode) {
		this.nowTownCode = nowTownCode == null ? null : nowTownCode.trim();
	}

	public String getNowVillageCode() {
		return nowVillageCode;
	}

	public void setNowVillageCode(String nowVillageCode) {
		this.nowVillageCode = nowVillageCode == null ? null : nowVillageCode
				.trim();
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode == null ? null : lccCode.trim();
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode == null ? null : patientCode.trim();
	}

	public String getBloodCode() {
		return bloodCode;
	}

	public void setBloodCode(String bloodCode) {
		this.bloodCode = bloodCode == null ? null : bloodCode.trim();
	}

	public String getIsRisk() {
		return isRisk;
	}

	public void setIsRisk(String isRisk) {
		this.isRisk = isRisk == null ? null : isRisk.trim();
	}

	public Date getRiskDate() {
		return riskDate;
	}

	public void setRiskDate(Date riskDate) {
		this.riskDate = riskDate;
	}

	public String getIsFollowview() {
		return isFollowview;
	}

	public void setIsFollowview(String isFollowview) {
		this.isFollowview = isFollowview == null ? null : isFollowview.trim();
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
		this.isFollowrisk = isFollowrisk == null ? null : isFollowrisk.trim();
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
		this.riskCode = riskCode == null ? null : riskCode.trim();
	}

	public Short getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Short isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public Integer getState010() {
		return state010;
	}

	public void setState010(Integer state010) {
		this.state010 = state010;
	}

	public Integer getState011() {
		return state011;
	}

	public void setState011(Integer state011) {
		this.state011 = state011;
	}

	public Integer getIsSystemDiabetes() {
		return isSystemDiabetes;
	}

	public void setIsSystemDiabetes(Integer isSystemDiabetes) {
		this.isSystemDiabetes = isSystemDiabetes;
	}

	public Integer getIsDiabetes() {
		return isDiabetes;
	}

	public void setIsDiabetes(Integer isDiabetes) {
		this.isDiabetes = isDiabetes;
	}

	public Integer getState012() {
		return state012;
	}

	public void setState012(Integer state012) {
		this.state012 = state012;
	}

	public Integer getState002002() {
		return state002002;
	}

	public void setState002002(Integer state002002) {
		this.state002002 = state002002;
	}
	
}