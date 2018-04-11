package com.bdcor.pip.web.data.domain;

import java.text.ParseException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdcor.pip.client.tools.DateUtils;
import com.bdcor.pip.core.utils.DateUtil;

public class PipCommPatientDate extends PipCommPatientDateKey {
	private String patientName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstStartDate;
	private String firstStartDateStr;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstEndDate;
	private String firstEndDateStr;
	private String firstDateStr;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sixPlanDate;
	private String sixPlanDateStr;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sixRealDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date twelvePlanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date twelveRealDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eighteenPlanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eighteenRealDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endPlanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endRealDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstPlanDate ;
	private String firstPlanDateStr ;
	public String getFirstPlanDateStr() {
		return DateUtils.fromatDateTime("yyyy-MM-dd", getFirstPlanDate());
	}
	private String nowRemark;
	public void setFirstPlanDate(Date firstPlanDate) {
		this.firstPlanDate = firstPlanDate;
	}
	public Date getFirstPlanDate() {
		return firstPlanDate;
	}
	/**
	 * firstEndDateStr.
	 * 
	 * @param firstEndDateStr
	 *            the firstEndDateStr to set
	 */
	/**
	 * sixPlanDateStr.
	 * 
	 * @return the sixPlanDateStr
	 */
	public String getSixPlanDateStr() {
		return DateUtil.dateToString(getSixPlanDate(), "yyyy-MM-dd");
	}

	public void setFirstEndDateStr(String firstEndDateStr) {
		this.firstEndDateStr = firstEndDateStr;
	}

	/**
	 * firstStartDateStr.
	 * 
	 * @param firstStartDateStr
	 *            the firstStartDateStr to set
	 */
	public void setFirstStartDateStr(String firstStartDateStr) {
		this.firstStartDateStr = firstStartDateStr;
	}

	/**
	 * firstStartDateStr.
	 * 
	 * @return the firstStartDateStr
	 */
	public String getFirstStartDateStr() throws ParseException {
		return DateUtil.dateToString(getFirstStartDate(), "yyyy-MM-dd");
	}

	/**
	 * firstEndDateStr.
	 * 
	 * @return the firstEndDateStr
	 * @throws Exception
	 */
	public String getFirstEndDateStr() throws Exception {
		return DateUtil.dateToString(getFirstEndDate(), "yyyy-MM-dd");
	}

	/**
	 * firstEndDate.
	 * 
	 * @return the firstEndDate
	 */
	public Date getFirstEndDate() throws Exception {
		return com.bdcor.pip.client.tools.DateUtils
				.addDate(getFirstDate(), 180);
	}

	/**
	 * firstStartDate.
	 * 
	 * @return the firstStartDate
	 * @throws ParseException
	 */
	public Date getFirstStartDate() throws ParseException {
		return com.bdcor.pip.client.tools.DateUtils
				.addDate(getFirstDate(), 150);
	}

	/**
	 * firstDateStr.
	 * 
	 * @param firstDateStr
	 *            the firstDateStr to set
	 */
	public void setFirstDateStr(String firstDateStr) {
		this.firstDateStr = firstDateStr;
	}

	/**
	 * firstEndDate.
	 * 
	 * @param firstEndDate
	 *            the firstEndDate to set
	 */
	public void setFirstEndDate(Date firstEndDate) {
		this.firstEndDate = firstEndDate;
	}

	/**
	 * firstStartDate.
	 * 
	 * @param firstStartDate
	 *            the firstStartDate to set
	 */
	public void setFirstStartDate(Date firstStartDate) {
		this.firstStartDate = firstStartDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public String getFirstDateStr() {
		return DateUtil.dateToString(getFirstDate(), "yyyy-MM-dd");
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getSixPlanDate() {
		return sixPlanDate;
	}

	public void setSixPlanDate(Date sixPlanDate) {
		this.sixPlanDate = sixPlanDate;
	}

	public Date getSixRealDate() {
		return sixRealDate;
	}

	public void setSixRealDate(Date sixRealDate) {
		this.sixRealDate = sixRealDate;
	}

	public Date getTwelvePlanDate() {
		return twelvePlanDate;
	}

	public void setTwelvePlanDate(Date twelvePlanDate) {
		this.twelvePlanDate = twelvePlanDate;
	}

	public Date getTwelveRealDate() {
		return twelveRealDate;
	}

	public void setTwelveRealDate(Date twelveRealDate) {
		this.twelveRealDate = twelveRealDate;
	}

	public Date getEighteenPlanDate() {
		return eighteenPlanDate;
	}

	public void setEighteenPlanDate(Date eighteenPlanDate) {
		this.eighteenPlanDate = eighteenPlanDate;
	}

	public Date getEighteenRealDate() {
		return eighteenRealDate;
	}

	public void setEighteenRealDate(Date eighteenRealDate) {
		this.eighteenRealDate = eighteenRealDate;
	}

	public Date getEndPlanDate() {
		return endPlanDate;
	}

	public void setEndPlanDate(Date endPlanDate) {
		this.endPlanDate = endPlanDate;
	}

	public Date getEndRealDate() {
		return endRealDate;
	}

	public void setEndRealDate(Date endRealDate) {
		this.endRealDate = endRealDate;
	}

	public String getNowRemark() {
		return nowRemark;
	}

	public void setNowRemark(String nowRemark) {
		this.nowRemark = nowRemark;
	}
}