package com.bdcor.pip.client.vo.paper;

import java.util.ArrayList;
import java.util.List;

import com.bdcor.pip.client.vo.paper.Paper.OrderProperty;
import com.bdcor.pip.client.vo.paper.QuestionOption.OptionValidate;
import com.bdcor.pip.client.xml.model.TempletAttributeDocumentBean.Attribute;
import com.bdcor.pip.client.xml.model.TempletQuestionSetDocumentBean.QuestionSet;

/**
 * 试题组
 * @author Administrator
 *
 */
public class QuestionGroup extends OrderProperty{
	
	private String displayname;
	private String controlShow;
	private String controlHide;
	private String iscycle;
	
	public QuestionGroup(){}
	public QuestionGroup(QuestionSet qg){
		super.setId(qg.getId());
		super.setOrder(qg.getOrder().intValue());
		this.displayname = qg.getDisplayname();
		
		
		Attribute[] qAttrs = qg.getAttributeArray();
		for(Attribute qAttr:qAttrs){
			String attrType = qAttr.getType();
			if(attrType!=null){
				String attrValue = qAttr.getValue();
				if("controlshow".equals(attrType)){
					this.controlShow = attrValue;
				}else if("controlhide".equals(attrType)){
					this.controlHide = attrValue;
				}else if("iscycle".equals(attrType) && "1".equals(attrValue)){
					iscycle = "1";
				}			
			}
		}
	}
	private List<QuestionC> qs = new ArrayList<QuestionC>();
	public List<QuestionC> getQs() {
		return qs;
	}
	public void setQs(List<QuestionC> qs) {
		this.qs = qs;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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
	public String getIscycle() {
		return iscycle;
	}
	public void setIscycle(String iscycle) {
		this.iscycle = iscycle;
	}
	
}
