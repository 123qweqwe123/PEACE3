package com.bdcor.pip.web.fee.domain;

public class ProjectCost { 
	private String id;
	private String st_type;      //阶段类型
	private String st_name;      //阶段中文名称
	private String lcc_fee_total; //阶段总费用 = 人数* 单位费用  即 pat_sum*type_sum
	private String type_cost;  // 阶段单位花费
	private String lcc_id;    // 协作中心ID
	private String lcc_name;  // 协作中心名称
	private String pat_sum;   // 本类型已经访问的人数     
	 
	  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType_cost() {
		return type_cost;
	}
	public void setType_cost(String type_cost) {
		this.type_cost = type_cost;
	}
	public String getSt_type() {
		return st_type;
	}
	public void setSt_type(String st_type) {
		this.st_type = st_type;
	}
	public String getSt_name() {
		return st_name;
	}
	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}
	public String getPat_sum() {
		return pat_sum;
	}
	public void setPat_sum(String pat_sum) {
		this.pat_sum = pat_sum;
	}
	public String getLcc_fee_total() {
		return lcc_fee_total;
	}
	public void setLcc_fee_total(String lcc_fee_total) {
		this.lcc_fee_total = lcc_fee_total;
	}
	public String getType_sum() {
		return type_cost;
	}
	public void setType_sum(String type_sum) {
		this.type_cost = type_sum;
	}
	public String getLcc_id() {
		return lcc_id;
	}
	public void setLcc_id(String lcc_id) {
		this.lcc_id = lcc_id;
	}
	public String getLcc_name() {
		return lcc_name;
	}
	public void setLcc_name(String lcc_name) {
		this.lcc_name = lcc_name;
	}
}
