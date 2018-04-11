package com.bdcor.pip.data.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "questionnaire")  
@XmlAccessorType(XmlAccessType.FIELD)
public class Questionnaire {

	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="doctype")
	private Doctype doctype;
	
	@XmlElement(name="QuestionSet")
	private List<QuestionSet> questionSetList;

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

	public List<QuestionSet> getQuestionSetList() {
		return questionSetList;
	}

	public void setQuestionSetList(List<QuestionSet> questionSetList) {
		this.questionSetList = questionSetList;
	} 

	
	
}
