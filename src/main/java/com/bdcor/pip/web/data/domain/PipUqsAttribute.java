package com.bdcor.pip.web.data.domain;

import java.util.Date;

public class PipUqsAttribute {
    private String attributeId;

    private String optionId;

    private String questionId;

    private String questionsetId;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Short isRemoved;

    private String remark;

    private String questionnaireId;

    private String projectId;

    private String validateobjecttype;

    private String validatetype;

    private String type;

    private String value;

    private String version;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionsetId() {
        return questionsetId;
    }

    public void setQuestionsetId(String questionsetId) {
        this.questionsetId = questionsetId;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Short getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Short isRemoved) {
        this.isRemoved = isRemoved;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getValidateobjecttype() {
        return validateobjecttype;
    }

    public void setValidateobjecttype(String validateobjecttype) {
        this.validateobjecttype = validateobjecttype;
    }

    public String getValidatetype() {
        return validatetype;
    }

    public void setValidatetype(String validatetype) {
        this.validatetype = validatetype;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}