package com.bdcor.pip.web.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PipUqsAttributeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipUqsAttributeExample() {
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

        public Criteria andAttributeIdIsNull() {
            addCriterion("ATTRIBUTE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAttributeIdIsNotNull() {
            addCriterion("ATTRIBUTE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAttributeIdEqualTo(String value) {
            addCriterion("ATTRIBUTE_ID =", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdNotEqualTo(String value) {
            addCriterion("ATTRIBUTE_ID <>", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdGreaterThan(String value) {
            addCriterion("ATTRIBUTE_ID >", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE_ID >=", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdLessThan(String value) {
            addCriterion("ATTRIBUTE_ID <", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdLessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE_ID <=", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdLike(String value) {
            addCriterion("ATTRIBUTE_ID like", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdNotLike(String value) {
            addCriterion("ATTRIBUTE_ID not like", value, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdIn(List<String> values) {
            addCriterion("ATTRIBUTE_ID in", values, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdNotIn(List<String> values) {
            addCriterion("ATTRIBUTE_ID not in", values, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE_ID between", value1, value2, "attributeId");
            return (Criteria) this;
        }

        public Criteria andAttributeIdNotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE_ID not between", value1, value2, "attributeId");
            return (Criteria) this;
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

        public Criteria andValidateobjecttypeIsNull() {
            addCriterion("VALIDATEOBJECTTYPE is null");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeIsNotNull() {
            addCriterion("VALIDATEOBJECTTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeEqualTo(String value) {
            addCriterion("VALIDATEOBJECTTYPE =", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeNotEqualTo(String value) {
            addCriterion("VALIDATEOBJECTTYPE <>", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeGreaterThan(String value) {
            addCriterion("VALIDATEOBJECTTYPE >", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeGreaterThanOrEqualTo(String value) {
            addCriterion("VALIDATEOBJECTTYPE >=", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeLessThan(String value) {
            addCriterion("VALIDATEOBJECTTYPE <", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeLessThanOrEqualTo(String value) {
            addCriterion("VALIDATEOBJECTTYPE <=", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeLike(String value) {
            addCriterion("VALIDATEOBJECTTYPE like", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeNotLike(String value) {
            addCriterion("VALIDATEOBJECTTYPE not like", value, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeIn(List<String> values) {
            addCriterion("VALIDATEOBJECTTYPE in", values, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeNotIn(List<String> values) {
            addCriterion("VALIDATEOBJECTTYPE not in", values, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeBetween(String value1, String value2) {
            addCriterion("VALIDATEOBJECTTYPE between", value1, value2, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidateobjecttypeNotBetween(String value1, String value2) {
            addCriterion("VALIDATEOBJECTTYPE not between", value1, value2, "validateobjecttype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeIsNull() {
            addCriterion("VALIDATETYPE is null");
            return (Criteria) this;
        }

        public Criteria andValidatetypeIsNotNull() {
            addCriterion("VALIDATETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andValidatetypeEqualTo(String value) {
            addCriterion("VALIDATETYPE =", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeNotEqualTo(String value) {
            addCriterion("VALIDATETYPE <>", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeGreaterThan(String value) {
            addCriterion("VALIDATETYPE >", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeGreaterThanOrEqualTo(String value) {
            addCriterion("VALIDATETYPE >=", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeLessThan(String value) {
            addCriterion("VALIDATETYPE <", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeLessThanOrEqualTo(String value) {
            addCriterion("VALIDATETYPE <=", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeLike(String value) {
            addCriterion("VALIDATETYPE like", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeNotLike(String value) {
            addCriterion("VALIDATETYPE not like", value, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeIn(List<String> values) {
            addCriterion("VALIDATETYPE in", values, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeNotIn(List<String> values) {
            addCriterion("VALIDATETYPE not in", values, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeBetween(String value1, String value2) {
            addCriterion("VALIDATETYPE between", value1, value2, "validatetype");
            return (Criteria) this;
        }

        public Criteria andValidatetypeNotBetween(String value1, String value2) {
            addCriterion("VALIDATETYPE not between", value1, value2, "validatetype");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("VALUE is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("VALUE =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("VALUE <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("VALUE >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("VALUE <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("VALUE <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("VALUE like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("VALUE not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("VALUE in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("VALUE not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("VALUE between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("VALUE not between", value1, value2, "value");
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