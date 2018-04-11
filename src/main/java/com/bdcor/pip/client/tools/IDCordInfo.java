package com.bdcor.pip.client.tools;



import java.io.File;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bdcor.pip.client.file.filter.XmlContentFilter;
import com.bdcor.pip.client.file.filter.XmlFileFilter;

/**
 * 身份证信息 
 * @author Administrator
 *
 */
public class IDCordInfo {

	public String name;
	public String gender;
	public String nation;
	public String id;
	public String brithday;
	public String addr;
	public String authorizedBy;
	public String startDay;
	public String endDay;
	
	public String code;
	
	
	
	public IDCordInfo(){}
	
	public IDCordInfo(IDCordInfo cordInfo){
		this.name = cordInfo.name;
		this.gender = cordInfo.gender;
		this.nation = cordInfo.nation;
		this.id = cordInfo.id;
		this.brithday = cordInfo.brithday;
		this.addr = cordInfo.addr;
		this.authorizedBy = cordInfo.authorizedBy;
		this.startDay = cordInfo.startDay;
		this.endDay = cordInfo.endDay;
	}
	
	public IDCordInfo(String name,String gender,String nation,String id,String brithday,String addr,String authorizedBy,String startDay,String endDay){
		this.name = name.trim();
		this.gender = gender.trim();
		this.nation = nation.trim();
		this.id = id.trim();
		this.brithday = brithday.trim();
		this.addr = addr.trim();
		this.authorizedBy = authorizedBy.trim();
		this.startDay = startDay.trim();
		this.endDay = endDay.trim();
	}
	
	
	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getNation() {
		return nation;
	}

	public String getId() {
		return id;
	}

	public String getBrithday() {
		return brithday;
	}

	public String getAddr() {
		return addr;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public String getStartDay() {
		return startDay;
	}

	public String getEndDay() {
		return endDay;
	}
	
	public String toString(){
		return "姓名："+this.name+";\n"+"身份证号："+this.id+";\n";
	}
	
	/**
	 * 身份证的xml文件，
	 * @param intervCode
	 * @param haspic 是否有头像
	 * @param isCryp  是否加密
	 * @return
	 */
	public String toXml(String intervCode,boolean hasPic,boolean isCryp){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("<IDCORD>");
		sb.append("<cpIntervCode>"+intervCode+"</cpIntervCode>");
		sb.append("<NAME>"+StringUtils.stripToEmpty(this.name)+"</NAME>");
		sb.append("<GENDER>"+StringUtils.stripToEmpty(this.gender)+"</GENDER>");
		sb.append("<NATION>"+StringUtils.stripToEmpty(this.nation)+"</NATION>");
		sb.append("<ID>"+StringUtils.stripToEmpty(this.id)+"</ID>");
		
		String brithday = StringUtils.stripToEmpty(this.brithday);
		if(!"".equals(brithday)){
			if(brithday.contains(".")){
				brithday = brithday.replaceAll("\\.", "-");
			}
			if(brithday.contains("/")){
				brithday = brithday.replaceAll("\\/", "-");
			}
		}
		sb.append("<BRITHDAY>"+brithday+"</BRITHDAY>");
		
		sb.append("<ADDR>"+StringUtils.stripToEmpty(this.addr)+"</ADDR>");
		sb.append("<AUTHORIZEDBY>"+StringUtils.stripToEmpty(this.authorizedBy)+"</AUTHORIZEDBY>");
		sb.append("<STARTDAY>"+StringUtils.stripToEmpty(this.startDay)+"</STARTDAY>");
		sb.append("<ENDDAY>"+StringUtils.stripToEmpty(this.endDay)+"</ENDDAY>");
		
	//	sb.append("<ID_CREATE_BY>"+CurrentLoginUser.userCodeOfCLogin()+"</ID_CREATE_BY>");
		sb.append("<ID_CREATE_DATE>"+DateUtils.fromatDateTime(null,new Date())+"</ID_CREATE_DATE>");
		sb.append("<ID_NUMBER_STATUS>"+(hasPic?"1":"2")+"</ID_NUMBER_STATUS>");
		
		
		
		sb.append("</IDCORD>");
		
		if(isCryp){
			String reStr = sb.toString();
			return new XmlContentFilter().encryptStr(reStr);
		}
		return sb.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	
	
	//=========================
	public String xmlFilePath;
	
	public String getXmlFilePath() {
		return xmlFilePath;
	}
	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	
	@Deprecated
	public static String addCordToXml(String oldXml,String intervCode){
		String insertStr = "<cpIntervCode>"+intervCode+"</cpIntervCode>";
		int end = oldXml.indexOf("<NAME>");
		String str1 = oldXml.substring(0,end);
		String str2 = oldXml.substring(end);
		return str1+insertStr+str2;
		
	}
	
	
}
