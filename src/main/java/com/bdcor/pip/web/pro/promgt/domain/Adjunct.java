package com.bdcor.pip.web.pro.promgt.domain;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

public class Adjunct {

	private String adjunctId;
	private String projectId;
	private String adjunctName;
	private String path;
	private String type;
	private String creator;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creatTime;
	private Integer downCount;
	private String fileDesc;
	
	@Transient
	private String typeName;
	
	public Adjunct(){}
	
	public Adjunct(String adjunctId, String projectId, String adjunctName,
			String path, String type, String creator, Date creatTime,
			Integer downCount, String fileDesc) {
		super();
		this.adjunctId = adjunctId;
		this.projectId = projectId;
		this.adjunctName = adjunctName;
		this.path = path;
		this.type = type;
		this.creator = creator;
		this.creatTime = creatTime;
		this.downCount = downCount;
		this.fileDesc = fileDesc;
	}
	
	public String getAdjunctId() {
		return adjunctId;
	}
	public void setAdjunctId(String adjunctId) {
		this.adjunctId = adjunctId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getAdjunctName() {
		return adjunctName;
	}
	public void setAdjunctName(String adjunctName) {
		this.adjunctName = adjunctName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Integer getDownCount() {
		return downCount;
	}
	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	
}
