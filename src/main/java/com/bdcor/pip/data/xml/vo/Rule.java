package com.bdcor.pip.data.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Rule {

	private String ruleid;
	
	private String expression;
	
	private List<Operation> operationList;

	@XmlAttribute(name = "ruleid")
	public String getRuleid() {
		return ruleid;
	}

	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}

	@XmlAttribute(name = "expression")
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@XmlElement(name = "operation")
	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}
	
	
}
