package com.bdcor.pip.web.qn.domain;

public class PipUqsAnswerKey {
    private String optionId;

    private String patientId;

    private String projectId;

    private String questionnaireId;

    private String questionsetId;

    private String questionId;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionsetId() {
        return questionsetId;
    }

    public void setQuestionsetId(String questionsetId) {
        this.questionsetId = questionsetId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}