package com.bdcor.pip.client.tools;



import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Interviewee {

	private String id;
	
	private String name;
	
	private String sex;
	
	private String idCard;
	
	private String age;
	
	private String address;
	
	private String national;
	
	private String birthday;
	
	private boolean isInterv;
	
	private String mobile;
	
	
	private String projId;
	private String patientId;
	private String commPersonCode;
	private String nation;
	private String credentialsType;
	private String credentialsCode;
	private String phone;
	private String email;
	private String maritalStatus;
	private String educational;
	private String postCode;
	private String nativeProvCode;
	private String nativeCityCode;
	private String nativeCountyCode;
	private String nativeTownCode;
	private String nativeVilageCode;
	private String nowProvCode;
	private String nowCityCode;
	private String nowCountyCode;
	private String nowTownCode;
	private String nowVilageCode;
	private String checkList;
	private String helpCode;
	
	//更新后，是否同步到服务器中
	private Integer cpIsUpload = 1;
							//===================更新日期
							private Date cpUploadDate;
							//===================更新人
							private String cpUploadUser;
	
	//高危受访者  条形码
	private String cpIntervRiskCode;
	
	//受访者 初筛条形码
	private String cpIntervCode;
	
	private String isScream;
	
	//是否初筛条件
	private Boolean cpIsMatchScreen;
	//是否签订知情同意书
	private Boolean cpIsConsent;
							//===================初筛登记日期
							private Date cpScreemRegisterDate;
							//===================初筛登记人
							private String cpScreemRegisterUser;
	
	
	//随访日期
	private Date cpAppointmentDate;
							//===================随访登记日期
							private Date cpAppointmentRegisterDate;
							//===================随访登记人
							private String cpAppointmentRegisterUser;
							
	//高危预约日期
	private Date followRiskDate;
	
	//是否特殊对象
	private Boolean cpIsSpecial;
	
	//新联系方式
	private String cpNewPhone;
	
	private Timestamp sUpdateTime;
	
	//是否高危登记
	private boolean isRiskRegister;
	//高危登记时间
	private Date riskRegisterDate;
	//高危登记调查代码
	private String riskRegisterOperator;
	
	
	
	
	
	
	public Interviewee(){}
	
	public Interviewee(IDCordInfo idCord){
		this.name = idCord.name;
		this.sex = idCord.gender;
		this.idCard = idCord.id;
		this.address = idCord.addr;
		this.national = idCord.nation;
		this.birthday = idCord.brithday;
		this.cpIntervCode = idCord.code;
		this.nation = idCord.nation;
	}
	
	
	public static class IntervGatherPro{
		private String id;
		private String cpIntervCode = null;
		private String cpIntervRiskCode = null;
		private Date followRiskDate = null;

		private Date cpAppointmentDate = null;
		private Boolean cpIsSpecial = null;
		private Integer cpIsUpload = null;
		private String exportId = null;
		
		private int dataType;
		private String createUser;
		private Date createDate;
		
		
		public String objToXml(){
			StringBuffer sb = new StringBuffer();
			sb.append("<patient>");
			//初筛形码
			sb.append("<patientCode>"+StringUtils.stripToEmpty(this.getCpIntervCode())+"</patientCode>");
			//高危条形码
			sb.append("<cpIntervRiskCode>"+StringUtils.stripToEmpty(this.getCpIntervRiskCode())+"</cpIntervRiskCode>");
			//随访预约日期
			sb.append("<cpAppointmentRegisterDate>"+StringUtils.stripToEmpty(DateUtils.fromatDateTime("yyyy-MM-dd", this.getCpAppointmentDate()))+"</cpAppointmentRegisterDate>");
			//高危预约日期
			sb.append("<followRiskDate>"+StringUtils.stripToEmpty(DateUtils.fromatDateTime("yyyy-MM-dd", this.getFollowRiskDate()))+"</followRiskDate>");
			//是否特殊对象
			sb.append("<isSpecial>"+(this.isCpIsSpecial()==null?"":this.isCpIsSpecial())+"</isSpecial>");
			//数据类型
			sb.append("<dataType>"+(this.dataType)+"</dataType>");
			
			//操作人
			sb.append("<createBy>"+(this.createUser==null?"":this.createUser)+"</createBy>");
			//操作时间
			sb.append("<createDate>"+StringUtils.stripToEmpty(DateUtils.fromatDateTime(null, this.createDate))+"</createDate>");
			
			
			sb.append("</patient>");
			return sb.toString();
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getExportId() {
			return exportId;
		}
		public void setExportId(String exportId) {
			this.exportId = exportId;
		}
		public String getCpIntervCode() {
			return cpIntervCode;
		}
		public void setCpIntervCode(String cpIntervCode) {
			this.cpIntervCode = cpIntervCode;
		}
		public String getCpIntervRiskCode() {
			return cpIntervRiskCode;
		}
		public void setCpIntervRiskCode(String cpIntervRiskCode) {
			this.cpIntervRiskCode = cpIntervRiskCode;
		}
		public Date getFollowRiskDate() {
			return followRiskDate;
		}
		public void setFollowRiskDate(Date followRiskDate) {
			this.followRiskDate = followRiskDate;
		}
		public Date getCpAppointmentDate() {
			return cpAppointmentDate;
		}
		public void setCpAppointmentDate(Date cpAppointmentDate) {
			this.cpAppointmentDate = cpAppointmentDate;
		}
		public Boolean isCpIsSpecial() {
			return cpIsSpecial;
		}
		
		public void setCpIsSpecial(boolean cpIsSpecial) {
			this.cpIsSpecial = cpIsSpecial;
		}
		public Integer isCpIsUpload() {
			return cpIsUpload;
		}
		public void setCpIsUpload(Integer cpIsUpload) {
			this.cpIsUpload = cpIsUpload;
		}

		public int getDataType() {
			return dataType;
		}

		public void setDataType(int dataType) {
			this.dataType = dataType;
		}

		public String getCreateUser() {
			return createUser;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		
	}
	
	public String getIsScream() {
		if(this.cpIntervCode!=null && !"".equals(this.cpIntervCode)){
			return "是";
		}else{
			return "否";
		}
	}

	public void setIsScream(String isScream) {
		this.isScream = isScream;
	}

	public Boolean getCpIsSpecial() {
		return cpIsSpecial;
	}

	public void setCpIsSpecial(Boolean cpIsSpecial) {
		this.cpIsSpecial = cpIsSpecial;
	}

	public String getCpNewPhone() {
		return cpNewPhone;
	}

	public String getCpIntervRiskCode() {
		return cpIntervRiskCode;
	}

	public void setCpIntervRiskCode(String cpIntervRiskCode) {
		this.cpIntervRiskCode = cpIntervRiskCode;
	}

	public void setCpNewPhone(String cpNewPhone) {
		this.cpNewPhone = cpNewPhone;
	}



	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getCommPersonCode() {
		return commPersonCode;
	}

	public Timestamp getsUpdateTime() {
		return sUpdateTime;
	}

	public void setsUpdateTime(Timestamp sUpdateTime) {
		this.sUpdateTime = sUpdateTime;
	}

	public void setCommPersonCode(String commPersonCode) {
		this.commPersonCode = commPersonCode;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
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

	public String getNativeProvCode() {
		return nativeProvCode;
	}

	public void setNativeProvCode(String nativeProvCode) {
		this.nativeProvCode = nativeProvCode;
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

	public String getNativeVilageCode() {
		return nativeVilageCode;
	}

	public void setNativeVilageCode(String nativeVilageCode) {
		this.nativeVilageCode = nativeVilageCode;
	}

	public String getNowProvCode() {
		return nowProvCode;
	}

	public void setNowProvCode(String nowProvCode) {
		this.nowProvCode = nowProvCode;
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

	public String getNowVilageCode() {
		return nowVilageCode;
	}

	public void setNowVilageCode(String nowVilageCode) {
		this.nowVilageCode = nowVilageCode;
	}

	public String getCheckList() {
		return checkList;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNational() {
		if(national==null || "null".equals(this.national)){
			return "";
		}
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public boolean getIsInterv() {
		return isInterv;
	}

	public void setInterv(boolean isInterv) {
		this.isInterv = isInterv;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public Integer isCpIsUpload() {
		return cpIsUpload;
	}

	public void setCpIsUpload(Integer cpIsUpload) {
		this.cpIsUpload = cpIsUpload;
	}

	public Date getCpUploadDate() {
		return cpUploadDate;
	}

	public void setCpUploadDate(Date cpUploadDate) {
		this.cpUploadDate = cpUploadDate;
	}

	public String getCpUploadUser() {
		return cpUploadUser;
	}

	public void setCpUploadUser(String cpUploadUser) {
		this.cpUploadUser = cpUploadUser;
	}

	public String getCpIntervCode() {
		return cpIntervCode;
	}

	public void setCpIntervCode(String cpIntervCode) {
		this.cpIntervCode = cpIntervCode;
	}

	public Boolean isCpIsMatchScreen() {
		return cpIsMatchScreen;
	}

	public void setCpIsMatchScreen(boolean cpIsMatchScreen) {
		this.cpIsMatchScreen = cpIsMatchScreen;
	}

	public Boolean isCpIsConsent() {
		return cpIsConsent;
	}

	public void setCpIsConsent(boolean cpIsConsent) {
		this.cpIsConsent = cpIsConsent;
	}

	public Date getCpScreemRegisterDate() {
		return cpScreemRegisterDate;
	}

	public void setCpScreemRegisterDate(Date cpScreemRegisterDate) {
		this.cpScreemRegisterDate = cpScreemRegisterDate;
	}

	public String getCpScreemRegisterUser() {
		return cpScreemRegisterUser;
	}

	public void setCpScreemRegisterUser(String cpScreemRegisterUser) {
		this.cpScreemRegisterUser = cpScreemRegisterUser;
	}

	public Date getCpAppointmentDate() {
		return cpAppointmentDate;
	}

	public void setCpAppointmentDate(Date cpAppointmentDate) {
		this.cpAppointmentDate = cpAppointmentDate;
	}

	public Date getCpAppointmentRegisterDate() {
		return cpAppointmentRegisterDate;
	}

	public void setCpAppointmentRegisterDate(Date cpAppointmentRegisterDate) {
		this.cpAppointmentRegisterDate = cpAppointmentRegisterDate;
	}

	public String getCpAppointmentRegisterUser() {
		return cpAppointmentRegisterUser;
	}

	public void setCpAppointmentRegisterUser(String cpAppointmentRegisterUser) {
		this.cpAppointmentRegisterUser = cpAppointmentRegisterUser;
	}

	public Date getFollowRiskDate() {
		return followRiskDate;
	}

	public void setFollowRiskDate(Date followRiskDate) {
		this.followRiskDate = followRiskDate;
	}
	
	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public boolean isRiskRegister() {
		return isRiskRegister;
	}

	public void setRiskRegister(boolean isRiskRegister) {
		this.isRiskRegister = isRiskRegister;
	}

	public Date getRiskRegisterDate() {
		return riskRegisterDate;
	}

	public void setRiskRegisterDate(Date riskRegisterDate) {
		this.riskRegisterDate = riskRegisterDate;
	}

	public String getRiskRegisterOperator() {
		return riskRegisterOperator;
	}

	public void setRiskRegisterOperator(String riskRegisterOperator) {
		this.riskRegisterOperator = riskRegisterOperator;
	}

	public String updateProToXml(){
		StringBuffer sb = new StringBuffer();
			sb.append("<patient>");
			//id
			sb.append("<patientId>"+StringUtils.trimToEmpty(this.getPatientId())+"</patientId>");
			//姓名
			sb.append("<patientName>"+StringUtils.trimToEmpty(this.getName())+"</patientName>");
			//身份证号
			sb.append("<patientIdCard>"+StringUtils.trimToEmpty(this.getIdCard())+"</patientIdCard>");
			//初筛形码
			sb.append("<patientCode>"+StringUtils.trimToEmpty(this.getCpIntervCode())+"</patientCode>");
			//高危条形码
			sb.append("<cpIntervRiskCode>"+StringUtils.trimToEmpty(this.getCpIntervRiskCode())+"</cpIntervRiskCode>");
			//是否初筛条件
			sb.append("<cpIsMatchScreen>"+(this.isCpIsMatchScreen()==null?"":this.isCpIsMatchScreen())+"</cpIsMatchScreen>");
			//是否签订知情同意书
			sb.append("<cpIsConsent>"+(this.isCpIsConsent()==null?"":this.isCpIsConsent())+"</cpIsConsent>");
			//随访预约日期
			sb.append("<cpAppointmentRegisterDate>"+StringUtils.trimToEmpty(DateUtils.fromatDateTime("yyyy-MM-dd", this.getCpAppointmentDate()))+"</cpAppointmentRegisterDate>");
			//高危预约日期
			sb.append("<followRiskDate>"+StringUtils.trimToEmpty(DateUtils.fromatDateTime("yyyy-MM-dd", this.getFollowRiskDate()))+"</followRiskDate>");
			//是否特殊对象
			sb.append("<isSpecial>"+(this.getCpIsSpecial()==null?"":this.getCpIsSpecial())+"</isSpecial>");
			//初筛登记人
			sb.append("<REG_CREATE_BY>"+(this.getCpScreemRegisterUser()==null?"":this.getCpScreemRegisterUser())+"</REG_CREATE_BY>");
			//初筛登记时间
			sb.append("<REG_CREATE_DATE>"+StringUtils.trimToEmpty(DateUtils.fromatDateTime(null, this.cpScreemRegisterDate))+"</REG_CREATE_DATE>");
			
			//=============================================高危登记
			//是否高危登记
//			sb.append("<IS_RISK_REGISTER>"+(StringUtils.isBlank(this.isRiskRegister+"")?"":this.isRiskRegister)+"</IS_RISK_REGISTER>");
			//高危登记时间
//			sb.append("<RISK_REGISTERDATE>"+StringUtils.trimToEmpty(DateUtils.fromatDateTime(null, this.riskRegisterDate))+"</RISK_REGISTERDATE>");
			//高危登记调查代码
//			sb.append("<RISK_REGISTEROPERATOR>"+StringUtils.trimToEmpty(this.riskRegisterOperator)+"</RISK_REGISTEROPERATOR>");
			
			
			sb.append("<cpNewPhone>"+StringUtils.trimToEmpty(this.getCpNewPhone())+"</cpNewPhone>");
			sb.append("</patient>");
			return sb.toString();
	}
	
	

}
