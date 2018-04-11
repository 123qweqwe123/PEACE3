package com.bdcor.pip.client.vo.paper;

import java.util.ArrayList;
import java.util.List;

import com.bdcor.pip.client.vo.paper.QuestionOption.OptionValidate;
import com.bdcor.pip.client.xml.model.TempletAttributeDocumentBean.Attribute;
import com.bdcor.pip.client.xml.model.TempletTableDocumentBean.Table;
import com.bdcor.pip.client.xml.model.TempletThDocumentBean.Th;
import com.bdcor.pip.client.xml.model.TempletTrDocumentBean.Tr;
/**
 * 表格
 * @author Administrator
 *
 */
public class QuestionTable {
	private String displayname;
	private List<QTableTr> tableTr;
	
	public QuestionTable(Table t){
		this.displayname = t.getDisplayname();
		if(t.getTrArray().length>0){
			this.tableTr = new ArrayList<QTableTr>();
			for(Tr tr:t.getTrArray()){
				this.tableTr.add(new QTableTr(tr));
			}
		}
	}
	
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public List<QTableTr> getTableTr() {
		return tableTr;
	}
	public void setTableTr(List<QTableTr> tableTr) {
		this.tableTr = tableTr;
	}
	


	/**
	 * 表格行
	 * @author Administrator
	 *
	 */
	public static class QTableTr{
		private String displayname;
		private int rownum;
		private List<QTableTrTd> tableTrTd;
		
		public QTableTr(Tr tr){
			this.displayname = tr.getDisplayname();
			this.rownum = Integer.parseInt(tr.getRownum());
			if(tr.getThArray().length>0){
				this.tableTrTd = new ArrayList<QTableTrTd>();
				for(Th th:tr.getThArray()){
					tableTrTd.add(new QTableTrTd(th));
				}
			}
		}
		public String getDisplayname() {
			return displayname;
		}
		public void setDisplayname(String displayname) {
			this.displayname = displayname;
		}
		public int getRownum() {
			return rownum;
		}
		public void setRownum(int rownum) {
			this.rownum = rownum;
		}
		public List<QTableTrTd> getTableTrTd() {
			return tableTrTd;
		}
		public void setTableTrTd(List<QTableTrTd> tableTrTd) {
			this.tableTrTd = tableTrTd;
		}

		/**
		 * 表格 单元格
		 * @author Administrator
		 *
		 */
		public static class QTableTrTd extends QuestionOption{	
			public Integer extendcolumn;
			public QTableTrTd(Th th){
				Attribute[] qAttrs = th.getAttributeArray();
				if(qAttrs != null && qAttrs.length > 0){
					for(Attribute qAttr:qAttrs){
						String attrType = qAttr.getType();
						String attrValue = qAttr.getValue();
						String validateobjecttype = qAttr.getValidateobjecttype();
						String validatetype = qAttr.getValidatetype();
						if("extendcolumn".equals(attrType)){
							this.extendcolumn = Integer.parseInt(attrValue.trim());
						}else if("controlshow".equals(attrType)){
							super.setControlShow(attrValue);
						}else if("controlhide".equals(attrType)){
							super.setControlHide(attrValue);
						}else if("sethidden".equals(attrType)  && "1".equals(attrValue)){ //初始隐藏
							super.setSetHidden("1");
						}else if("isnull".equals(attrType) && "1".equals(attrValue)){
							super.setNotNull("1");
						}else if("ischosennull".equals(attrType)  && "1".equals(attrValue)){
							super.setIsChosenNull("1");
						}else if("after_content_display".equals(attrType)){
							super.setAfterDisplayName(attrValue);
						}else if("before_content_display".equals(attrType)){
							super.setBeforeDisplayName(attrValue);
						}else if("readonly".equals(attrType) && "1".equals(attrValue)){
							super.setReadOnly("1");
						}else if("rejectother".equals(attrType) && "1".equals(attrValue)){
							super.setRejectother( "1");
						}else if("alert".equals(attrType)){
							super.setAlert(attrValue);
						}else if("allend".equals(attrType) && "1".equals(attrValue)){
							super.setAllEnd("1");
						}else if("maxlength".equals(attrType)){
							super.setMaxLength(Integer.parseInt(attrValue.trim()));
						}else if("calculate".equals(attrType)){
							super.setCalculate(attrValue);
						}else if("char".equals(validateobjecttype)  && "1".equals(attrValue)){
							super.setCharType(validatetype);
						}else if("number".equals(validateobjecttype)){
							super.setTextType("number");
						}else if("datenum".equals(validateobjecttype)){
							super.setTextType("datenum");
						}else if("calculateandvalidate".equals(validateobjecttype)){
							super.setCalculateandvalidate(attrValue);
						}
						
						if("format".equals(validatetype)){
							super.setFormat(attrValue);
						}else if("validate".equals(attrType) && "scope".equals(validatetype)){
							super.setScope(attrValue);
						}else if("constrainvalidate".equals(attrType) && "scope".equals(validatetype)){
							super.setConstrainScope(attrValue);
						}
					}
				}
				super.setId(th.getId());
				super.setDisplayname(th.getDisplayname());
				super.setDatatype(th.getDatatype());
				super.setOrder(th.getOrder().intValue());
				super.setDatastyle(th.getColstyle());
				super.setCode(th.getCode());
			}
			public Integer getExtendcolumn() {
				return extendcolumn;
			}
			public void setExtendcolumn(Integer extendcolumn) {
				this.extendcolumn = extendcolumn;
			}
		}
	}
	
	
}
