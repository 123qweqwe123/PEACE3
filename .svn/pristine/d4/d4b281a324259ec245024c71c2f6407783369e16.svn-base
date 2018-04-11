package com.bdcor.pip.client.vo.paper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bdcor.pip.client.vo.paper.Paper.OrderProperty;
import com.bdcor.pip.client.xml.model.TempletAttributeDocumentBean.Attribute;
import com.bdcor.pip.client.xml.model.TempletOptionDocumentBean.Option;

public class QuestionOption extends OrderProperty {


	//===================================节点属性
	private String displayname;
	private DataType datatype;
	private QuesionType datastyle;
	private String code;
	
	//====================================子节点
	
	private QuestionOption coption;//暂时不用
	//====================================衍生值
	
	private String controlShow;
	private String controlHide;
	private String setHidden;
	private String isChosenNull;//针对单选填空和多选填空
	private String afterDisplayName;
	private String beforeDisplayName;
	private String readOnly;//只读
	private String rejectother;//选中这个时其他禁用
	private String alert;//选中这个时弹出提示
	private String allEnd;//非空
	private String notNull;//非空
	private Integer maxLength;//填空最大长度
	private String calculate;//计算本空的值
	private String charType;//字符类型
	private String textType;//
	private String idCard;
	private String isdefaultchosen;//选项是否选中
	
	private String cycle;
	
	
	private String format;//
	private String scope;//取值范围
	private String constrainScope;//强制取值范围
	private String calculateandvalidate;
	
	private String minDate="";
	private String maxDate="";
	
	

	public QuestionOption(){}
	public QuestionOption(Option option){
		super(option.getId(),option.getOrder().intValue());
		this.displayname = option.getDisplayname();
		this.code = option.getCode();
		this.setDatatype(option.getDatatype());
		this.setDatastyle(option.getDatastyle());
		Attribute[] qAttrs = option.getAttributeArray();
		for(Attribute qAttr:qAttrs){
			String attrType = qAttr.getType();
			if(attrType!=null){
				String attrValue = qAttr.getValue();
				String validateobjecttype = qAttr.getValidateobjecttype();
				String validatetype = qAttr.getValidatetype();
				if("controlshow".equals(attrType)){
					this.controlShow = attrValue;
				}else if("controlhide".equals(attrType)){
					this.controlHide = attrValue;
				}else if("sethidden".equals(attrType)  && "1".equals(attrValue)){ //初始隐藏
					this.setHidden = "1";
				}else if("isnull".equals(attrType) && "1".equals(attrValue)){
					this.notNull = "1";
				}else if("ischosennull".equals(attrType)  && "1".equals(attrValue)){
					this.isChosenNull = "1";
				}else if("after_content_display".equals(attrType)){
					this.afterDisplayName = attrValue;
				}else if("before_content_display".equals(attrType)){
					this.beforeDisplayName = attrValue;
				}else if("readonly".equals(attrType) && "1".equals(attrValue)){
					this.readOnly = "1";
				}else if("rejectother".equals(attrType) && "1".equals(attrValue)){
					this.rejectother = "1";
				}else if("alert".equals(attrType)){
					this.alert = attrValue;
				}else if("allend".equals(attrType) && "1".equals(attrValue)){
					this.allEnd = "1";
				}else if("maxlength".equals(attrType)){
					maxLength = Integer.parseInt(attrValue.trim());
				}else if("calculate".equals(attrType)){
					calculate = attrValue;
				}else if("idcard".equals(attrType) && "1".equals(attrValue)){
					idCard = "1";
				}else if("char".equals(validateobjecttype)  && "1".equals(attrValue)){
					charType = validatetype;
				}else if("number".equals(validateobjecttype)){
					textType = "number";
				}else if("datenum".equals(validateobjecttype)){
					textType = "datenum";
				}else if("calculateandvalidate".equals(attrType)){
					calculateandvalidate = attrValue;
				}else if("cycle".equals(attrType)  && "1".equals(attrValue)){
					cycle = "1";
				}else if("isdefaultchosen".equals(attrType)  && "1".equals(attrValue)){
					isdefaultchosen = "1";
				}
				
				if("format".equals(validatetype)){
					format = attrValue;
				}else if("validate".equals(attrType) && "scope".equals(validatetype)){
					scope = attrValue;
				}else if("constrainvalidate".equals(attrType) && "scope".equals(validatetype)){
					if(this.datatype == DataType.DATE || this.datatype == DataType.DATETIME){
						minDate=getDateStr(attrValue,this.datatype)[0];
						maxDate=getDateStr(attrValue,this.datatype)[1];
					}else{
						constrainScope = attrValue;
					}
					
				}
				
			}
		}
	}

	private String[] getDateStr(String attrValue, DataType datatype) {
		String[] returnArr = {"",""};
		if(attrValue==null || attrValue.trim().length()==0)return returnArr;
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String[] tempArr = attrValue.replaceAll(" ","").replaceAll("T",sf.format(new Date())).split(",");
		if(tempArr.length!=2)return returnArr;
		
		try{
			if(tempArr[0].length()==11){
				Date beginDate = sf.parse(tempArr[0].substring(1));
				if(tempArr[0].substring(0,1).equals("(")){
					beginDate.setDate(beginDate.getDate()+1);
				}
				returnArr[0]=sf.format(beginDate);
				if(datatype == DataType.DATETIME){
					returnArr[0] = returnArr[0] +" "+"00:00:00";
				}
			}
		}catch (Exception e) {
		}
		
		try{
			if(tempArr[1].length()==11){
				Date endDate = sf.parse(tempArr[1].substring(0,tempArr[1].length()-1));
				if(tempArr[1].substring(tempArr[1].length()-1,tempArr[1].length()).equals(")")){
					endDate.setDate(endDate.getDate()-1);
				}
				returnArr[1]=sf.format(endDate);
				if(datatype == DataType.DATETIME){
					returnArr[1] = returnArr[1] +" "+"23:59:59";
				}
			}
		}catch (Exception e) {
		}

		return returnArr;
	}
	protected void setDatatype(String value){
		if(value==null){
			this.datatype = null;
			return;
		}
		String valueUp = value.toUpperCase().trim();
		if(valueUp.contains(DATA_TYPE_VARCHER)){
			this.datatype = DataType.VARCHER;
			this.maxLength = Integer.parseInt(valueUp.substring(valueUp.indexOf("(")+1, valueUp.indexOf(")")).split(",")[0])/3;
		}else if(valueUp.contains(DATA_TYPE_DATETIME)){
			this.datatype = DataType.DATETIME;
		}else if(valueUp.contains(DATA_TYPE_DATE)){
			this.datatype = DataType.DATE;
		}else if(valueUp.contains(DATA_TYPE_TIME)){
			this.datatype = DataType.TIME;
		}else if(valueUp.contains(DATA_TYPE_NUMBER)){
			this.datatype = DataType.NUMBER;
			this.maxLength = Integer.parseInt(valueUp.substring(valueUp.indexOf("(")+1, valueUp.indexOf(")")).split(",")[0])/3;
		}
		
	}
	protected void setDatastyle(String value){
		if(value==null){
			this.datastyle = null;
			return ;
		}
		String type = value.toUpperCase();
		if(Q_TYPE_SINGLE.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_SINGLE;
		}else if(Q_TYPE_SINGLE_FILL.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_SINGLE_FILL;
		}else if(Q_TYPE_MULTI.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_MULTI;
		}else if(Q_TYPE_MULTI_FILL.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_MULTI_FILL;
		}else if(Q_TYPE_FILL_BLANK.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_FILL_BLANK;
		}else if(Q_TYPE_TABLE.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_TABLE;
		}else if(Q_TYPE_TABLE_SINGLE.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_TABLE_SINGLE;
		}else if(Q_TYPE_TABLE_MULTI.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_TABLE_MULTI;
		}else if(Q_TYPE_TABLE_BLANK.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_TABLE_BLANK;
		}else if(Q_TYPE_TABLE_SELECT.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_TABLE_SELECT;
		}else if(Q_TYPE_TEXTAREA.equals(type)){
			this.datastyle = QuesionType.Q_TYPE_TEXTAREA;
		}
	}
	
	public static class OptionValidate{
		private String type;
		private String value;
		public OptionValidate(){}
		public OptionValidate(String type,String value){
			this.type = type;
			this.value = value;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
	}
	
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public DataType getDatatype() {
		return datatype;
	}
	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}
	public QuesionType getDatastyle() {
		return datastyle;
	}
	public void setDatastyle(QuesionType datastyle) {
		this.datastyle = datastyle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAfterDisplayName() {
		return afterDisplayName;
	}
	public void setAfterDisplayName(String afterDisplayName) {
		this.afterDisplayName = afterDisplayName;
	}
	public QuestionOption getCoption() {
		return coption;
	}
	public void setCoption(QuestionOption coption) {
		this.coption = coption;
	}
	public String getBeforeDisplayName() {
		return beforeDisplayName;
	}
	public void setBeforeDisplayName(String beforeDisplayName) {
		this.beforeDisplayName = beforeDisplayName;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getNotNull() {
		return notNull;
	}
	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}
	public String getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}
	public String getRejectother() {
		return rejectother;
	}
	public void setRejectother(String rejectother) {
		this.rejectother = rejectother;
	}
	public String getIsChosenNull() {
		return isChosenNull;
	}
	public void setIsChosenNull(String isChosenNull) {
		this.isChosenNull = isChosenNull;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getAllEnd() {
		return allEnd;
	}
	public void setAllEnd(String allEnd) {
		this.allEnd = allEnd;
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
	public String getCalculate() {
		return calculate;
	}
	public void setCalculate(String calculate) {
		this.calculate = calculate;
	}
	public String getCharType() {
		return charType;
	}
	public void setCharType(String charType) {
		this.charType = charType;
	}
	public String getTextType() {
		return textType;
	}
	public void setTextType(String textType) {
		this.textType = textType;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getConstrainScope() {
		return constrainScope;
	}
	public void setConstrainScope(String constrainScope) {
		this.constrainScope = constrainScope;
	}
	public String getCalculateandvalidate() {
		return calculateandvalidate;
	}
	public void setCalculateandvalidate(String calculateandvalidate) {
		this.calculateandvalidate = calculateandvalidate;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getIsdefaultchosen() {
		return isdefaultchosen;
	}
	public void setIsdefaultchosen(String isdefaultchosen) {
		this.isdefaultchosen = isdefaultchosen;
	}
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	
}
