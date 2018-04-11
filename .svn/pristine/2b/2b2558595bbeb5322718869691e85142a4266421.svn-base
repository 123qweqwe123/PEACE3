package com.bdcor.pip.data.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "questionnaire")  
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionaireRule {
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="doctype")
	private Doctype doctype;
		
	@XmlElement(name="questionset")
	private List<QuestionsetRule> questionsetList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Doctype getDoctype() {
		return doctype;
	}

	public void setDoctype(Doctype doctype) {
		this.doctype = doctype;
	}
	
	public List<QuestionsetRule> getQuestionsetList() {
		return questionsetList;
	}

	public void setQuestionsetList(List<QuestionsetRule> questionsetList) {
		this.questionsetList = questionsetList;
	}
		
}
