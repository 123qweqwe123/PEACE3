package com.bdcor.pip.client.vo.paper;

import java.util.ArrayList;
import java.util.List;

import com.bdcor.pip.client.vo.paper.Paper.OrderProperty;
import com.bdcor.pip.client.xml.model.TempletAttributeDocumentBean.Attribute;
import com.bdcor.pip.client.xml.model.TempletOptionDocumentBean.Option;
import com.bdcor.pip.client.xml.model.TempletQuestionDocumentBean.Question;

/**
 * 试题
 * 
 * 试题相关的常量定义.
 * 
 * @author Administrator
 *
 */
public class QuestionC extends OrderProperty{

	private String displayname;
	private QuesionType type;
	private String code;
	
	//attribut转换属性
	private String helpText;
	private String setHidden;
	private String controlShow;
	private String controlHide;
	private String notNull;
	private String updateReadOnly;
	private String fontSize="30";
	private String color="red";
	private String readOnly;
	
	private QuestionTable talbe;
	private List<QuestionOption> options = new ArrayList<QuestionOption>();
	
	public QuestionC(){}
	public QuestionC(Question q){
		//=============================================属性设置
		super(q.getId(),q.getOrder().intValue());
		this.displayname = q.getDisplayname();
		this.code = q.getCode();
		this.setType(q.getType());
		
		Attribute[] qAttrs = q.getAttributeArray();
		for(Attribute qAttr:qAttrs){
			String attrType = qAttr.getType();
			if(attrType!=null){
				String attrValue = qAttr.getValue();
				if("isnull".equals(attrType) && "1".equals(attrValue)){ //非空
					this.notNull = "1";
				}else if("sethidden".equals(attrType)  && "1".equals(attrValue)){ //初始隐藏
					this.setHidden = "1";
				}else if("readonly".equals(attrType)  && "1".equals(attrValue)){ //初始隐藏
					this.readOnly = "1";
				}else if("helptext".equals(attrType)){
					this.helpText = attrValue;
				}else if("controlshow".equals(attrType)){
					this.controlShow = attrValue;
				}else if("controlhide".equals(attrType)){
					this.controlHide = attrValue;
				}else if("UPDATEREADONLY".equals(attrType.toUpperCase())){
					this.updateReadOnly = attrValue;
				}else if("fontsize".equals(attrType)){
					this.fontSize = attrValue;
				}else if("color".equals(attrType)){
					this.color = attrValue;
				}
			}
		}
		//=============================================子项设置
		//表格
		if(q.getTable()!=null){
			this.talbe = new QuestionTable(q.getTable());
		}
		//添加选项
		if(q.getOptionArray().length>0){
			for(Option selection:q.getOptionArray()){
				QuestionOption option = new QuestionOption(selection);
				this.getOptions().add(option);
				if(selection.getOption()!=null){
					option.setCoption(new QuestionOption(selection.getOption()));
				}
			}
		}
		
	}
	private void setType(String typeStr){
		String type = typeStr.toUpperCase();
		if(Q_TYPE_SINGLE.equals(type)){
			this.type = QuesionType.Q_TYPE_SINGLE;
		}else if(Q_TYPE_SINGLE_FILL.equals(type)){
			this.type = QuesionType.Q_TYPE_SINGLE_FILL;
		}else if(Q_TYPE_MULTI.equals(type)){
			this.type = QuesionType.Q_TYPE_MULTI;
		}else if(Q_TYPE_MULTI_FILL.equals(type)){
			this.type = QuesionType.Q_TYPE_MULTI_FILL;
		}else if(Q_TYPE_FILL_BLANK.equals(type)){
			this.type = QuesionType.Q_TYPE_FILL_BLANK;
		}else if(Q_TYPE_TABLE.equals(type)){
			this.type = QuesionType.Q_TYPE_TABLE;
		}else if(Q_TYPE_TABLE_SINGLE.equals(type)){
			this.type = QuesionType.Q_TYPE_TABLE_SINGLE;
		}else if(Q_TYPE_TABLE_MULTI.equals(type)){
			this.type = QuesionType.Q_TYPE_TABLE_MULTI;
		}else if(Q_TYPE_TABLE_BLANK.equals(type)){
			this.type = QuesionType.Q_TYPE_TABLE_BLANK;
		}else if(Q_TYPE_TABLE_SELECT.equals(type)){
			this.type = QuesionType.Q_TYPE_TABLE_SELECT;
		}else if(Q_TYPE_SHOWTEXT.equals(type)){
			this.type = QuesionType.Q_TYPE_SHOWTEXT;
		}
	}
	
	
	public List<QuestionOption> getOptions() {
		return options;
	}
	public void setOptions(List<QuestionOption> options) {
		this.options = options;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public QuesionType getType() {
		return type;
	}
	public void setType(QuesionType type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public QuestionTable getTalbe() {
		return talbe;
	}
	public void setTalbe(QuestionTable talbe) {
		this.talbe = talbe;
	}
	public String getUpdateReadOnly() {
		return updateReadOnly;
	}
	public void setUpdateReadOnly(String updateReadOnly) {
		this.updateReadOnly = updateReadOnly;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}
	
}
