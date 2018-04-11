package com.bdcor.pip.web.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PipUqsOptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipUqsOptionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andOptionIdIsNull() {
            addCriterion("OPTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andOptionIdIsNotNull() {
            addCriterion("OPTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOptionIdEqualTo(String value) {
            addCriterion("OPTION_ID =", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotEqualTo(String value) {
            addCriterion("OPTION_ID <>", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdGreaterThan(String value) {
            addCriterion("OPTION_ID >", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_ID >=", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdLessThan(String value) {
            addCriterion("OPTION_ID <", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdLessThanOrEqualTo(String value) {
            addCriterion("OPTION_ID <=", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdLike(String value) {
            addCriterion("OPTION_ID like", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotLike(String value) {
            addCriterion("OPTION_ID not like", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdIn(List<String> values) {
            addCriterion("OPTION_ID in", values, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotIn(List<String> values) {
            addCriterion("OPTION_ID not in", values, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdBetween(String value1, String value2) {
            addCriterion("OPTION_ID between", value1, value2, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotBetween(String value1, String value2) {
            addCriterion("OPTION_ID not between", value1, value2, "optionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("QUESTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("QUESTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(String value) {
            addCriterion("QUESTION_ID =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(String value) {
            addCriterion("QUESTION_ID <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(String value) {
            addCriterion("QUESTION_ID >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_ID >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(String value) {
            addCriterion("QUESTION_ID <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_ID <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLike(String value) {
            addCriterion("QUESTION_ID like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotLike(String value) {
            addCriterion("QUESTION_ID not like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<String> values) {
            addCriterion("QUESTION_ID in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<String> values) {
            addCriterion("QUESTION_ID not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(String value1, String value2) {
            addCriterion("QUESTION_ID between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(String value1, String value2) {
            addCriterion("QUESTION_ID not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdIsNull() {
            addCriterion("QUESTIONSET_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdIsNotNull() {
            addCriterion("QUESTIONSET_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdEqualTo(String value) {
            addCriterion("QUESTIONSET_ID =", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdNotEqualTo(String value) {
            addCriterion("QUESTIONSET_ID <>", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdGreaterThan(String value) {
            addCriterion("QUESTIONSET_ID >", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTIONSET_ID >=", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdLessThan(String value) {
            addCriterion("QUESTIONSET_ID <", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdLessThanOrEqualTo(String value) {
            addCriterion("QUESTIONSET_ID <=", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdLike(String value) {
            addCriterion("QUESTIONSET_ID like", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdNotLike(String value) {
            addCriterion("QUESTIONSET_ID not like", value, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdIn(List<String> values) {
            addCriterion("QUESTIONSET_ID in", values, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdNotIn(List<String> values) {
            addCriterion("QUESTIONSET_ID not in", values, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdBetween(String value1, String value2) {
            addCriterion("QUESTIONSET_ID between", value1, value2, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionsetIdNotBetween(String value1, String value2) {
            addCriterion("QUESTIONSET_ID not between", value1, value2, "questionsetId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIsNull() {
            addCriterion("QUESTIONNAIRE_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIsNotNull() {
            addCriterion("QUESTIONNAIRE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdEqualTo(String value) {
            addCriterion("QUESTIONNAIRE_ID =", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotEqualTo(String value) {
            addCriterion("QUESTIONNAIRE_ID <>", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdGreaterThan(String value) {
            addCriterion("QUESTIONNAIRE_ID >", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTIONNAIRE_ID >=", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLessThan(String value) {
            addCriterion("QUESTIONNAIRE_ID <", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLessThanOrEqualTo(String value) {
            addCriterion("QUESTIONNAIRE_ID <=", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLike(String value) {
            addCriterion("QUESTIONNAIRE_ID like", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotLike(String value) {
            addCriterion("QUESTIONNAIRE_ID not like", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIn(List<String> values) {
            addCriterion("QUESTIONNAIRE_ID in", values, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotIn(List<String> values) {
            addCriterion("QUESTIONNAIRE_ID not in", values, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdBetween(String value1, String value2) {
            addCriterion("QUESTIONNAIRE_ID between", value1, value2, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotBetween(String value1, String value2) {
            addCriterion("QUESTIONNAIRE_ID not between", value1, value2, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("PROJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("PROJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("PROJECT_ID =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("PROJECT_ID <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("PROJECT_ID >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("PROJECT_ID <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("PROJECT_ID like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("PROJECT_ID not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("PROJECT_ID in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("PROJECT_ID not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("PROJECT_ID between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("PROJECT_ID not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNull() {
            addCriterion("DISPLAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNotNull() {
            addCriterion("DISPLAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameEqualTo(String value) {
            addCriterion("DISPLAY_NAME =", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotEqualTo(String value) {
            addCriterion("DISPLAY_NAME <>", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThan(String value) {
            addCriterion("DISPLAY_NAME >", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThanOrEqualTo(String value) {
            addCriterion("DISPLAY_NAME >=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThan(String value) {
            addCriterion("DISPLAY_NAME <", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThanOrEqualTo(String value) {
            addCriterion("DISPLAY_NAME <=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLike(String value) {
            addCriterion("DISPLAY_NAME like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotLike(String value) {
            addCriterion("DISPLAY_NAME not like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIn(List<String> values) {
            addCriterion("DISPLAY_NAME in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotIn(List<String> values) {
            addCriterion("DISPLAY_NAME not in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameBetween(String value1, String value2) {
            addCriterion("DISPLAY_NAME between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotBetween(String value1, String value2) {
            addCriterion("DISPLAY_NAME not between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andIsChooseableIsNull() {
            addCriterion("IS_CHOOSEABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsChooseableIsNotNull() {
            addCriterion("IS_CHOOSEABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsChooseableEqualTo(Short value) {
            addCriterion("IS_CHOOSEABLE =", value, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableNotEqualTo(Short value) {
            addCriterion("IS_CHOOSEABLE <>", value, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableGreaterThan(Short value) {
            addCriterion("IS_CHOOSEABLE >", value, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_CHOOSEABLE >=", value, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableLessThan(Short value) {
            addCriterion("IS_CHOOSEABLE <", value, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableLessThanOrEqualTo(Short value) {
            addCriterion("IS_CHOOSEABLE <=", value, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableIn(List<Short> values) {
            addCriterion("IS_CHOOSEABLE in", values, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableNotIn(List<Short> values) {
            addCriterion("IS_CHOOSEABLE not in", values, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableBetween(Short value1, Short value2) {
            addCriterion("IS_CHOOSEABLE between", value1, value2, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andIsChooseableNotBetween(Short value1, Short value2) {
            addCriterion("IS_CHOOSEABLE not between", value1, value2, "isChooseable");
            return (Criteria) this;
        }

        public Criteria andPictureAddressIsNull() {
            addCriterion("PICTURE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andPictureAddressIsNotNull() {
            addCriterion("PICTURE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andPictureAddressEqualTo(String value) {
            addCriterion("PICTURE_ADDRESS =", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressNotEqualTo(String value) {
            addCriterion("PICTURE_ADDRESS <>", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressGreaterThan(String value) {
            addCriterion("PICTURE_ADDRESS >", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressGreaterThanOrEqualTo(String value) {
            addCriterion("PICTURE_ADDRESS >=", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressLessThan(String value) {
            addCriterion("PICTURE_ADDRESS <", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressLessThanOrEqualTo(String value) {
            addCriterion("PICTURE_ADDRESS <=", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressLike(String value) {
            addCriterion("PICTURE_ADDRESS like", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressNotLike(String value) {
            addCriterion("PICTURE_ADDRESS not like", value, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressIn(List<String> values) {
            addCriterion("PICTURE_ADDRESS in", values, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressNotIn(List<String> values) {
            addCriterion("PICTURE_ADDRESS not in", values, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressBetween(String value1, String value2) {
            addCriterion("PICTURE_ADDRESS between", value1, value2, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andPictureAddressNotBetween(String value1, String value2) {
            addCriterion("PICTURE_ADDRESS not between", value1, value2, "pictureAddress");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdIsNull() {
            addCriterion("DATATYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdIsNotNull() {
            addCriterion("DATATYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdEqualTo(Long value) {
            addCriterion("DATATYPE_ID =", value, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdNotEqualTo(Long value) {
            addCriterion("DATATYPE_ID <>", value, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdGreaterThan(Long value) {
            addCriterion("DATATYPE_ID >", value, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DATATYPE_ID >=", value, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdLessThan(Long value) {
            addCriterion("DATATYPE_ID <", value, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdLessThanOrEqualTo(Long value) {
            addCriterion("DATATYPE_ID <=", value, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdIn(List<Long> values) {
            addCriterion("DATATYPE_ID in", values, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdNotIn(List<Long> values) {
            addCriterion("DATATYPE_ID not in", values, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdBetween(Long value1, Long value2) {
            addCriterion("DATATYPE_ID between", value1, value2, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andDatatypeIdNotBetween(Long value1, Long value2) {
            addCriterion("DATATYPE_ID not between", value1, value2, "datatypeId");
            return (Criteria) this;
        }

        public Criteria andIsEventIsNull() {
            addCriterion("IS_EVENT is null");
            return (Criteria) this;
        }

        public Criteria andIsEventIsNotNull() {
            addCriterion("IS_EVENT is not null");
            return (Criteria) this;
        }

        public Criteria andIsEventEqualTo(Short value) {
            addCriterion("IS_EVENT =", value, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventNotEqualTo(Short value) {
            addCriterion("IS_EVENT <>", value, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventGreaterThan(Short value) {
            addCriterion("IS_EVENT >", value, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_EVENT >=", value, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventLessThan(Short value) {
            addCriterion("IS_EVENT <", value, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventLessThanOrEqualTo(Short value) {
            addCriterion("IS_EVENT <=", value, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventIn(List<Short> values) {
            addCriterion("IS_EVENT in", values, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventNotIn(List<Short> values) {
            addCriterion("IS_EVENT not in", values, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventBetween(Short value1, Short value2) {
            addCriterion("IS_EVENT between", value1, value2, "isEvent");
            return (Criteria) this;
        }

        public Criteria andIsEventNotBetween(Short value1, Short value2) {
            addCriterion("IS_EVENT not between", value1, value2, "isEvent");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andIsRemovedIsNull() {
            addCriterion("IS_REMOVED is null");
            return (Criteria) this;
        }

        public Criteria andIsRemovedIsNotNull() {
            addCriterion("IS_REMOVED is not null");
            return (Criteria) this;
        }

        public Criteria andIsRemovedEqualTo(Short value) {
            addCriterion("IS_REMOVED =", value, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedNotEqualTo(Short value) {
            addCriterion("IS_REMOVED <>", value, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedGreaterThan(Short value) {
            addCriterion("IS_REMOVED >", value, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_REMOVED >=", value, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedLessThan(Short value) {
            addCriterion("IS_REMOVED <", value, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedLessThanOrEqualTo(Short value) {
            addCriterion("IS_REMOVED <=", value, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedIn(List<Short> values) {
            addCriterion("IS_REMOVED in", values, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedNotIn(List<Short> values) {
            addCriterion("IS_REMOVED not in", values, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedBetween(Short value1, Short value2) {
            addCriterion("IS_REMOVED between", value1, value2, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andIsRemovedNotBetween(Short value1, Short value2) {
            addCriterion("IS_REMOVED not between", value1, value2, "isRemoved");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDictValueIsNull() {
            addCriterion("DICT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDictValueIsNotNull() {
            addCriterion("DICT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDictValueEqualTo(String value) {
            addCriterion("DICT_VALUE =", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotEqualTo(String value) {
            addCriterion("DICT_VALUE <>", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueGreaterThan(String value) {
            addCriterion("DICT_VALUE >", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_VALUE >=", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLessThan(String value) {
            addCriterion("DICT_VALUE <", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLessThanOrEqualTo(String value) {
            addCriterion("DICT_VALUE <=", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLike(String value) {
            addCriterion("DICT_VALUE like", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotLike(String value) {
            addCriterion("DICT_VALUE not like", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueIn(List<String> values) {
            addCriterion("DICT_VALUE in", values, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotIn(List<String> values) {
            addCriterion("DICT_VALUE not in", values, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueBetween(String value1, String value2) {
            addCriterion("DICT_VALUE between", value1, value2, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotBetween(String value1, String value2) {
            addCriterion("DICT_VALUE not between", value1, value2, "dictValue");
            return (Criteria) this;
        }

        public Criteria andOptionTypeIsNull() {
            addCriterion("OPTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOptionTypeIsNotNull() {
            addCriterion("OPTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOptionTypeEqualTo(String value) {
            addCriterion("OPTION_TYPE =", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotEqualTo(String value) {
            addCriterion("OPTION_TYPE <>", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeGreaterThan(String value) {
            addCriterion("OPTION_TYPE >", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_TYPE >=", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeLessThan(String value) {
            addCriterion("OPTION_TYPE <", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeLessThanOrEqualTo(String value) {
            addCriterion("OPTION_TYPE <=", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeLike(String value) {
            addCriterion("OPTION_TYPE like", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotLike(String value) {
            addCriterion("OPTION_TYPE not like", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeIn(List<String> values) {
            addCriterion("OPTION_TYPE in", values, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotIn(List<String> values) {
            addCriterion("OPTION_TYPE not in", values, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeBetween(String value1, String value2) {
            addCriterion("OPTION_TYPE between", value1, value2, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotBetween(String value1, String value2) {
            addCriterion("OPTION_TYPE not between", value1, value2, "optionType");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andOptionStyleIsNull() {
            addCriterion("OPTION_STYLE is null");
            return (Criteria) this;
        }

        public Criteria andOptionStyleIsNotNull() {
            addCriterion("OPTION_STYLE is not null");
            return (Criteria) this;
        }

        public Criteria andOptionStyleEqualTo(String value) {
            addCriterion("OPTION_STYLE =", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleNotEqualTo(String value) {
            addCriterion("OPTION_STYLE <>", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleGreaterThan(String value) {
            addCriterion("OPTION_STYLE >", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_STYLE >=", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleLessThan(String value) {
            addCriterion("OPTION_STYLE <", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleLessThanOrEqualTo(String value) {
            addCriterion("OPTION_STYLE <=", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleLike(String value) {
            addCriterion("OPTION_STYLE like", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleNotLike(String value) {
            addCriterion("OPTION_STYLE not like", value, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleIn(List<String> values) {
            addCriterion("OPTION_STYLE in", values, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleNotIn(List<String> values) {
            addCriterion("OPTION_STYLE not in", values, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleBetween(String value1, String value2) {
            addCriterion("OPTION_STYLE between", value1, value2, "optionStyle");
            return (Criteria) this;
        }

        public Criteria andOptionStyleNotBetween(String value1, String value2) {
            addCriterion("OPTION_STYLE not between", value1, value2, "optionStyle");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}