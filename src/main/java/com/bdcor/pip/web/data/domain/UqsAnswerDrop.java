package com.bdcor.pip.web.data.domain;

import java.util.Date;

public class UqsAnswerDrop extends UqsAnswerDropKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_ANSWER_DROP.ANSWER
     *
     * @mbggenerated
     */
    private String answer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_ANSWER_DROP.CREATE_DATE
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_ANSWER_DROP.CREATE_ORGANIZATION
     *
     * @mbggenerated
     */
    private String createOrganization;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_ANSWER_DROP.DICT_VALUE_CODE
     *
     * @mbggenerated
     */
    private String dictValueCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PIP_UQS_ANSWER_DROP.DROP_DATE
     *
     * @mbggenerated
     */
    private Date dropDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_ANSWER_DROP.ANSWER
     *
     * @return the value of PIP_UQS_ANSWER_DROP.ANSWER
     *
     * @mbggenerated
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_ANSWER_DROP.ANSWER
     *
     * @param answer the value for PIP_UQS_ANSWER_DROP.ANSWER
     *
     * @mbggenerated
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_ANSWER_DROP.CREATE_DATE
     *
     * @return the value of PIP_UQS_ANSWER_DROP.CREATE_DATE
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_ANSWER_DROP.CREATE_DATE
     *
     * @param createDate the value for PIP_UQS_ANSWER_DROP.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_ANSWER_DROP.CREATE_ORGANIZATION
     *
     * @return the value of PIP_UQS_ANSWER_DROP.CREATE_ORGANIZATION
     *
     * @mbggenerated
     */
    public String getCreateOrganization() {
        return createOrganization;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_ANSWER_DROP.CREATE_ORGANIZATION
     *
     * @param createOrganization the value for PIP_UQS_ANSWER_DROP.CREATE_ORGANIZATION
     *
     * @mbggenerated
     */
    public void setCreateOrganization(String createOrganization) {
        this.createOrganization = createOrganization == null ? null : createOrganization.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_ANSWER_DROP.DICT_VALUE_CODE
     *
     * @return the value of PIP_UQS_ANSWER_DROP.DICT_VALUE_CODE
     *
     * @mbggenerated
     */
    public String getDictValueCode() {
        return dictValueCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_ANSWER_DROP.DICT_VALUE_CODE
     *
     * @param dictValueCode the value for PIP_UQS_ANSWER_DROP.DICT_VALUE_CODE
     *
     * @mbggenerated
     */
    public void setDictValueCode(String dictValueCode) {
        this.dictValueCode = dictValueCode == null ? null : dictValueCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PIP_UQS_ANSWER_DROP.DROP_DATE
     *
     * @return the value of PIP_UQS_ANSWER_DROP.DROP_DATE
     *
     * @mbggenerated
     */
    public Date getDropDate() {
        return dropDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PIP_UQS_ANSWER_DROP.DROP_DATE
     *
     * @param dropDate the value for PIP_UQS_ANSWER_DROP.DROP_DATE
     *
     * @mbggenerated
     */
    public void setDropDate(Date dropDate) {
        this.dropDate = dropDate;
    }
}