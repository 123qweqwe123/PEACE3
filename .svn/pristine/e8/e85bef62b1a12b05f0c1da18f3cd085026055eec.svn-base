package com.bdcor.pip.web.material.supp.domain;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdcor.pip.client.tools.DateUtils;

public class PipMmsExscmmaster {
	private String id;

	private String projectId;

	private String exorderNo;

	private String activeclassCode;
	// @Transient
	private String activeclassName; // 出库名称

	private String lccCode;
	
	private String lccName ;
	private String stockName;
	public void setLccName(String lccName) {
		this.lccName = lccName;
	}
	public String getLccName() {
		return lccName;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	private String userCode;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date exportDate;
	private String exportDateStr;
	public String getExportDateStr() {
		return DateUtils.fromatDateTime("yyyy-MM-dd", getExportDate());
	}
	private String userName;

	private String receiverCode;

	private String receiverName;

	private Date receiverDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	private String createDateStr;
	public String getCreateDateStr() {
		return DateUtils.fromatDateTime("yyyy-MM-dd", getCreateDate());
	}

	private String createBy;

	private Short isRemoved;

	private String stockCode;

	private String exportLccCode;
	private String exportLccName; // 目标单位

	private String exportStockCode;
	private String exportStockName; // 目标库房
	private Short importState; // 出库状态
	private String userId; // 用户id
	private List<PipMmsExscmdetal> pipMmsExscmdetals = new ArrayList<PipMmsExscmdetal>();
	public void setPipMmsExscmdetals(List<PipMmsExscmdetal> pipMmsExscmdetals) {
		this.pipMmsExscmdetals = pipMmsExscmdetals;
	}
	public List<PipMmsExscmdetal> getPipMmsExscmdetals() {
		return pipMmsExscmdetals;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getExorderNo() {
		return exorderNo;
	}

	public void setExorderNo(String exorderNo) {
		this.exorderNo = exorderNo;
	}

	public String getActiveclassCode() {
		return activeclassCode;
	}

	public void setActiveclassCode(String activeclassCode) {
		this.activeclassCode = activeclassCode;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getReceiverDate() {
		return receiverDate;
	}

	public void setReceiverDate(Date receiverDate) {
		this.receiverDate = receiverDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Short getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Short isRemoved) {
		this.isRemoved = isRemoved;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getExportLccCode() {
		return exportLccCode;
	}

	public void setExportLccCode(String exportLccCode) {
		this.exportLccCode = exportLccCode;
	}

	public String getExportStockCode() {
		return exportStockCode;
	}

	public void setExportStockCode(String exportStockCode) {
		this.exportStockCode = exportStockCode;
	}

	public Short getImportState() {
		return importState;
	}

	public void setImportState(Short importState) {
		this.importState = importState;
	}

	public String getActiveclassName() {
		return activeclassName;
	}

	public void setActiveclassName(String activeclassName) {
		this.activeclassName = activeclassName;
	}

	public String getExportLccName() {
		return exportLccName;
	}

	public void setExportLccName(String exportLccName) {
		this.exportLccName = exportLccName;
	}

	public String getExportStockName() {
		return exportStockName;
	}

	public void setExportStockName(String exportStockName) {
		this.exportStockName = exportStockName;
	}

}