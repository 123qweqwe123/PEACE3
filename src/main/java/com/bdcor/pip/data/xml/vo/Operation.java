package com.bdcor.pip.data.xml.vo;

import javax.xml.bind.annotation.XmlAttribute;

public class Operation {

	private String state;
	
	private String questionsetid;
	
	private String questionid;
	
	private String optionid;

	@XmlAttribute(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlAttribute(name = "questionsetid")
	public String getQuestionsetid() {
		return questionsetid;
	}

	public void setQuestionsetid(String questionsetid) {
		this.questionsetid = questionsetid;
	}

	@XmlAttribute(name = "questionid")
	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	@XmlAttribute(name = "optionid")
	public String getOptionid() {
		return optionid;
	}

	public void setOptionid(String optionid) {
		this.optionid = optionid;
	}

	
}
