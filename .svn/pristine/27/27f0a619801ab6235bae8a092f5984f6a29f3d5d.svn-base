package com.bdcor.pip.client.tools;



import java.io.Serializable;
import java.util.Date;


public class BoolData implements Serializable{
	private String id = null;
	private String pid = null;
	private String typeStr = null;
	private String type = null;
	private String value = null;
	private String unit = null;
	private String createTimeStr = null;
	private Date transTime = null;
	
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTime) {
		this.createTimeStr = createTime;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public void init(){
		this.setType(null);
		this.setTypeStr(null);
		this.setValue(null);
		this.setUnit(null);
		this.setCreateTimeStr(null);
	}
	public String toString(){
		return "pid:"+this.pid+
				" typeStr:"+this.typeStr+
				" type:"+this.type+
				" value:"+this.value+
				" unit:"+this.unit+
				" createTimeStr:"+this.createTimeStr+
				" transTime:"+DateUtils.fromatDateTime("yyyy-MM-dd HH:mm:ss", this.transTime);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
