package com.bdcor.pip.web.data.domain;

import java.util.Date;

public class PipUqsQuestionset {
    private String questionsetId;

    private String questionnaireId;

    private String projectId;

    private String displayName;

    private String description;

    private Long orderInQuestionnaire;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Short isRemoved;

    private String remark;

    private String version;

    public String getQuestionsetId() {
        return questionsetId;
    }

    public void setQuestionsetId(String questionsetId) {
        this.questionsetId = questionsetId;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrderInQuestionnaire() {
        return orderInQuestionnaire;
    }

    public void setOrderInQuestionnaire(Long orderInQuestionnaire) {
        this.orderInQuestionnaire = orderInQuestionnaire;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}