package com.bdcor.pip.web.data.domain;

import java.util.Date;

public class UqsQuestion extends UqsQuestionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.DISPLAY_NAME
     *
     * @mbggenerated
     */
    private String displayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.ORDER_IN_QUESTIONSET
     *
     * @mbggenerated
     */
    private Long orderInQuestionset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.QUESTIONTYPE_ID
     *
     * @mbggenerated
     */
    private Long questiontypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.PICTURE_ADDRESS
     *
     * @mbggenerated
     */
    private String pictureAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.CREATE_BY
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.CREATE_DATE
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.UPDATE_BY
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.UPDATE_DATE
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.IS_REMOVED
     *
     * @mbggenerated
     */
    private Short isRemoved;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.REMARK
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.IS_RULE
     *
     * @mbggenerated
     */
    private Short isRule;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.QUESTION_CODE
     *
     * @mbggenerated
     */
    private String questionCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_QUESTION.QUESTION_TYPE
     *
     * @mbggenerated
     */
    private String questionType;
    
    private String version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.DISPLAY_NAME
     *
     * @return the value of PIP_UQS_QUESTION.DISPLAY_NAME
     *
     * @mbggenerated
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.DISPLAY_NAME
     *
     * @param displayName the value for PIP_UQS_QUESTION.DISPLAY_NAME
     *
     * @mbggenerated
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.ORDER_IN_QUESTIONSET
     *
     * @return the value of PIP_UQS_QUESTION.ORDER_IN_QUESTIONSET
     *
     * @mbggenerated
     */
    public Long getOrderInQuestionset() {
        return orderInQuestionset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.ORDER_IN_QUESTIONSET
     *
     * @param orderInQuestionset the value for PIP_UQS_QUESTION.ORDER_IN_QUESTIONSET
     *
     * @mbggenerated
     */
    public void setOrderInQuestionset(Long orderInQuestionset) {
        this.orderInQuestionset = orderInQuestionset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.QUESTIONTYPE_ID
     *
     * @return the value of PIP_UQS_QUESTION.QUESTIONTYPE_ID
     *
     * @mbggenerated
     */
    public Long getQuestiontypeId() {
        return questiontypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.QUESTIONTYPE_ID
     *
     * @param questiontypeId the value for PIP_UQS_QUESTION.QUESTIONTYPE_ID
     *
     * @mbggenerated
     */
    public void setQuestiontypeId(Long questiontypeId) {
        this.questiontypeId = questiontypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.PICTURE_ADDRESS
     *
     * @return the value of PIP_UQS_QUESTION.PICTURE_ADDRESS
     *
     * @mbggenerated
     */
    public String getPictureAddress() {
        return pictureAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.PICTURE_ADDRESS
     *
     * @param pictureAddress the value for PIP_UQS_QUESTION.PICTURE_ADDRESS
     *
     * @mbggenerated
     */
    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress == null ? null : pictureAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.CREATE_BY
     *
     * @return the value of PIP_UQS_QUESTION.CREATE_BY
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.CREATE_BY
     *
     * @param createBy the value for PIP_UQS_QUESTION.CREATE_BY
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.CREATE_DATE
     *
     * @return the value of PIP_UQS_QUESTION.CREATE_DATE
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.CREATE_DATE
     *
     * @param createDate the value for PIP_UQS_QUESTION.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.UPDATE_BY
     *
     * @return the value of PIP_UQS_QUESTION.UPDATE_BY
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.UPDATE_BY
     *
     * @param updateBy the value for PIP_UQS_QUESTION.UPDATE_BY
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.UPDATE_DATE
     *
     * @return the value of PIP_UQS_QUESTION.UPDATE_DATE
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.UPDATE_DATE
     *
     * @param updateDate the value for PIP_UQS_QUESTION.UPDATE_DATE
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.IS_REMOVED
     *
     * @return the value of PIP_UQS_QUESTION.IS_REMOVED
     *
     * @mbggenerated
     */
    public Short getIsRemoved() {
        return isRemoved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.IS_REMOVED
     *
     * @param isRemoved the value for PIP_UQS_QUESTION.IS_REMOVED
     *
     * @mbggenerated
     */
    public void setIsRemoved(Short isRemoved) {
        this.isRemoved = isRemoved;
    }
    
    

    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.REMARK
     *
     * @return the value of PIP_UQS_QUESTION.REMARK
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.REMARK
     *
     * @param remark the value for PIP_UQS_QUESTION.REMARK
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.IS_RULE
     *
     * @return the value of PIP_UQS_QUESTION.IS_RULE
     *
     * @mbggenerated
     */
    public Short getIsRule() {
        return isRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.IS_RULE
     *
     * @param isRule the value for PIP_UQS_QUESTION.IS_RULE
     *
     * @mbggenerated
     */
    public void setIsRule(Short isRule) {
        this.isRule = isRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.QUESTION_CODE
     *
     * @return the value of PIP_UQS_QUESTION.QUESTION_CODE
     *
     * @mbggenerated
     */
    public String getQuestionCode() {
        return questionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.QUESTION_CODE
     *
     * @param questionCode the value for PIP_UQS_QUESTION.QUESTION_CODE
     *
     * @mbggenerated
     */
    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode == null ? null : questionCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_QUESTION.QUESTION_TYPE
     *
     * @return the value of PIP_UQS_QUESTION.QUESTION_TYPE
     *
     * @mbggenerated
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_QUESTION.QUESTION_TYPE
     *
     * @param questionType the value for PIP_UQS_QUESTION.QUESTION_TYPE
     *
     * @mbggenerated
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }
}