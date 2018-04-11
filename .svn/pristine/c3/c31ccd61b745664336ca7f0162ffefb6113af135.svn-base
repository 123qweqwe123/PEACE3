package com.bdcor.pip.web.quality.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 表格
 * @author Administrator
 *
 */
public class QuestionTable {
	private String displayName;
	private List<QTableTr> tableTr = new ArrayList<QuestionTable.QTableTr>();
	
	public QuestionTable(List<Option> options){
		if(options == null)return;
		Map<String,String> labelmap = new HashMap<String,String>();
		for(Option o : options){
			String[] arr = o.getOptionId().split("-");
			if(arr.length!=2)continue;
			QTableTr tr = getTrById(arr[0].trim());
			if(tr==null){
				tr = new  QTableTr(o);
				tr.setId(Integer.parseInt(arr[0].trim()));
				tableTr.add(tr);
			}
			if(!"0".equals(arr[1].trim())){
				tr.getTableTrTd().add(new QTableTr.QTableTrTd(o));
			}
			
		}
	}
	
	private QTableTr getTrById(String trId){
		if(trId==null || tableTr==null || tableTr.size()==0)return null;
		for(QTableTr tr:tableTr){
			if(trId.equals(String.valueOf(tr.getId()))){
				return tr;
			}
		}
		return null;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
		private int id;
		private String displayName;
		private List<QTableTrTd> tableTrTd = new ArrayList<QuestionTable.QTableTr.QTableTrTd>();

		public QTableTr(Option o) {
			this.id=Integer.parseInt(o.getOptionId().split("-")[0]);
			if(o.getDisplayName().indexOf(";")>-1){
				this.displayName = o.getDisplayName().split(";")[0];
			}
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public List<QTableTrTd> getTableTrTd() {
			return tableTrTd;
		}
		public void setTableTrTd(List<QTableTrTd> tableTrTd) {
			this.tableTrTd = tableTrTd;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		/**
		 * 表格 单元格
		 * @author Administrator
		 *
		 */
		public static class QTableTrTd extends Option{	
			public Integer extendcolumn;
			private String id;
			public QTableTrTd(Option o){
				List<Attribute> qAttrs = o.getAttributes();
				if(qAttrs != null && qAttrs.size() > 0){
					for(Attribute qAttr:qAttrs){
						String attrType = qAttr.getType();
						String attrValue = qAttr.getValue();
						if("extendcolumn".equals(attrType)){
							try{
								this.extendcolumn = Integer.parseInt(attrValue);
							}catch (Exception e) {
							}
						}
					}
				}
				this.id=o.getOptionId().split("-")[1];
				if(o.getDisplayName().indexOf(";")>-1){
					super.setDisplayName(o.getDisplayName().split(";")[1]);
				}else{
					super.setDisplayName(o.getDisplayName());
				}
				super.setDataStyle(o.getDataStyle());
				//super.setCode(o.getCode());
			}
			public Integer getExtendcolumn() {
				return extendcolumn;
			}
			public void setExtendcolumn(Integer extendcolumn) {
				this.extendcolumn = extendcolumn;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			
		}
	}
	
	
}
