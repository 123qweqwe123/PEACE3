package com.bdcor.pip.web.quality.domain;

import java.util.List;

public class Question {

	private String questionId;
	private String displayName;
	private String type;
	private List<Option> options;
	private QuestionTable table;
	private List<Attribute> attributes;
	
	private String helpText;
	private String setHidden;
	private String controlShow;
	private String controlHide;
	private String notNull;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		if("TABLE".equals(this.type)){
			table = new QuestionTable(options);
		}else{
			this.options = options;
		}
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
		if(attributes==null)return;
		for(Attribute qAttr:attributes){
			String attrType = qAttr.getType();
			if(attrType!=null){
				String attrValue = qAttr.getValue();
				if("isnull".equals(attrType) && "1".equals(attrValue)){ //非空
					this.notNull = "1";
				}else if("sethidden".equals(attrType)  && "1".equals(attrValue)){ //初始隐藏
					this.setHidden = "1";
				}else if("helptext".equals(attrType)){
					this.helpText = attrValue;
				}else if("controlshow".equals(attrType)){
					this.controlShow = attrValue;
				}else if("controlhide".equals(attrType)){
					this.controlHide = attrValue;
				}
			}
		}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if(type==null)return;
		this.type = type.toUpperCase();
	}
	public String getHelpText() {
		return helpText;
	}
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}
	public String getSetHidden() {
		return setHidden;
	}
	public void setSetHidden(String setHidden) {
		this.setHidden = setHidden;
	}
	public String getControlShow() {
		return controlShow;
	}
	public void setControlShow(String controlShow) {
		this.controlShow = controlShow;
	}
	public String getControlHide() {
		return controlHide;
	}
	public void setControlHide(String controlHide) {
		this.controlHide = controlHide;
	}
	public String getNotNull() {
		return notNull;
	}
	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}
	public QuestionTable getTable() {
		return table;
	}
	public void setTable(QuestionTable table) {
		this.table = table;
	}
	
	
}