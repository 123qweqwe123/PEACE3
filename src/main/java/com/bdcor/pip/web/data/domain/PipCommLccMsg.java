package com.bdcor.pip.web.data.domain;

import java.util.Date;

import org.apache.tools.ant.util.DateUtils;

public class PipCommLccMsg extends PipCommLccMsgKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PIP_COMM_LCC_MSG.MESSAGE
	 * @mbggenerated
	 */
	private String message;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PIP_COMM_LCC_MSG.IS_READ
	 * @mbggenerated
	 */
	private String isRead;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PIP_COMM_LCC_MSG.MESSAGE
	 * @return  the value of PIP_COMM_LCC_MSG.MESSAGE
	 * @mbggenerated
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PIP_COMM_LCC_MSG.MESSAGE
	 * @param message  the value for PIP_COMM_LCC_MSG.MESSAGE
	 * @mbggenerated
	 */
	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PIP_COMM_LCC_MSG.IS_READ
	 * @return  the value of PIP_COMM_LCC_MSG.IS_READ
	 * @mbggenerated
	 */
	public String getIsRead() {
		return isRead;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PIP_COMM_LCC_MSG.IS_READ
	 * @param isRead  the value for PIP_COMM_LCC_MSG.IS_READ
	 * @mbggenerated
	 */
	public void setIsRead(String isRead) {
		this.isRead = isRead == null ? null : isRead.trim();
	}
	
	
	private String createDateTxt;

	public String getCreateDateTxt() {
		createDateTxt = DateUtils.format(this.getCreateDate(), "yyyy-MM-dd hh:mm:ss");
		return createDateTxt;
	}

	public void setCreateDateTxt(String createDateTxt) {
		this.createDateTxt = createDateTxt;
	}
}