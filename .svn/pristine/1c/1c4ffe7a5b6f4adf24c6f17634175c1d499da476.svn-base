package com.bdcor.pip.web.quality.domain;

import java.util.List;

public class QuestionSet {

	private String questionSetId;
	private String displayName;
	private List<Question> questions;
	private List<Attribute> attributes;
	
	private String controlShow;
	private String controlHide;
	
	public String getQuestionSetId() {
		return questionSetId;
	}
	public void setQuestionSetId(String questionSetId) {
		this.questionSetId = questionSetId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
		if(attributes == null || attributes.size()==0)return;
		for(Attribute a:attributes){
			String type = a.getType();
			if(type!=null){
				String attrValue = a.getValue();
				if("controlshow".equals(type)){
					this.controlShow = attrValue;
				}else if("controlhide".equals(type)){
					this.controlHide = attrValue;
				}			
			}
		}
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
	
	
}