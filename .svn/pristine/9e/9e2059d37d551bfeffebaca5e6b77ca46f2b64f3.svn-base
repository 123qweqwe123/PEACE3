
package com.bdcor.pip.web.sys.rbac.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.bdcor.pip.core.mapper.DateAdapter;

@XmlRootElement(name="ssss")
public class CustomFunction {
	private String userId;
	private String functionId;

    //主键ID
	private String id;
	private String code;
	private String name;
	private String fvalue;
	
	private Date createDatetime;
	
	
	private Date updateDatetime;
	
	
	public String getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public String getCode() {
		return code;
	}
	public String getFvalue() {
		return fvalue;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	
	@XmlElement(name="createDatetime")  
    @XmlJavaTypeAdapter(DateAdapter.class) 
	public Date getCreateDatetime() {
		return  createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public Date getUpdateDatetime() {
		return  updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}