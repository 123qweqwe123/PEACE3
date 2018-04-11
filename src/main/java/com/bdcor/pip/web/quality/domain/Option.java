package com.bdcor.pip.web.quality.domain;

import java.util.List;

public class Option {

	private String displayName;
	private String optionId;
	private List<Attribute> attributes;
	private String dataStyle;
	private String code;
	
	private String controlShow;
	private String controlHide;
	private String setHidden;
	private String afterDisplayName;
	private String beforeDisplayName;
	private String allEnd;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
				if("controlshow".equals(attrType)){
					this.controlShow = attrValue;
				}else if("controlhide".equals(attrType)){
					this.controlHide = attrValue;
				}else if("sethidden".equals(attrType)  && "1".equals(attrValue)){ //初始隐藏
					this.setHidden = "1";
				}else if("after_content_display".equals(attrType)){
					this.afterDisplayName = attrValue;
				}else if("before_content_display".equals(attrType)){
					this.beforeDisplayName = attrValue;
				}else if("allend".equals(attrType) && "1".equals(attrValue)){
					this.allEnd = "1";
				}				
			}
		}
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getDataStyle() {
		return dataStyle;
	}
	public void setDataStyle(String dataStyle) {
		if(dataStyle==null)return;
		this.dataStyle = dataStyle.toUpperCase();
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
	public String getSetHidden() {
		return setHidden;
	}
	public void setSetHidden(String setHidden) {
		this.setHidden = setHidden;
	}
	public String getAfterDisplayName() {
		return afterDisplayName;
	}
	public void setAfterDisplayName(String afterDisplayName) {
		this.afterDisplayName = afterDisplayName;
	}
	public String getBeforeDisplayName() {
		return beforeDisplayName;
	}
	public void setBeforeDisplayName(String beforeDisplayName) {
		this.beforeDisplayName = beforeDisplayName;
	}
	public String getAllEnd() {
		return allEnd;
	}
	public void setAllEnd(String allEnd) {
		this.allEnd = allEnd;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}