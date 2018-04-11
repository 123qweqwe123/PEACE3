package com.bdcor.pip.web.material.supp.domain;

import java.util.Date;

public class PipMmsStorehouse {
	
	  
	    private int id;

	    private String projectId;

	    private String lccCode;

	    private String stockCode;
	    
	    private String stockName;
	    
	    private String  helpCode;

	    private String createBy;

	    private Date createDate;

	    private String isRemoved;
	
	    private String  remark;
	

	    public void PipMmsStorehouse(){}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getProjectId() {
			return projectId;
		}


		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}


		public String getLccCode() {
			return lccCode;
		}


		public void setLccCode(String lccCode) {
			this.lccCode = lccCode;
		}


		public String getStockCode() {
			return stockCode;
		}


		public void setStockCode(String stockCode) {
			this.stockCode = stockCode;
		}


		public String getStockName() {
			return stockName;
		}


		public void setStockName(String stockName) {
			this.stockName = stockName;
		}


		public String getHelpCode() {
			return helpCode;
		}


		public void setHelpCode(String helpCode) {
			this.helpCode = helpCode;
		}


		public String getCreateBy() {
			return createBy;
		}


		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}


		public Date getCreateDate() {
			return createDate;
		}


		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}


		public String getIsRemoved() {
			return isRemoved;
		}


		public void setIsRemoved(String isRemoved) {
			this.isRemoved = isRemoved;
		}


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		}
	   
	   
	   
	
	
	
	

}
