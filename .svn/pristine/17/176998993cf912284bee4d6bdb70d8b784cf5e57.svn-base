package com.bdcor.pip.web.qn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PipCommEvent1Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipCommEvent1Example() {
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

        public Criteria andLccCodeIsNull() {
            addCriterion("LCC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andLccCodeIsNotNull() {
            addCriterion("LCC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andLccCodeEqualTo(String value) {
            addCriterion("LCC_CODE =", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotEqualTo(String value) {
            addCriterion("LCC_CODE <>", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeGreaterThan(String value) {
            addCriterion("LCC_CODE >", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeGreaterThanOrEqualTo(String value) {
            addCriterion("LCC_CODE >=", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLessThan(String value) {
            addCriterion("LCC_CODE <", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLessThanOrEqualTo(String value) {
            addCriterion("LCC_CODE <=", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLike(String value) {
            addCriterion("LCC_CODE like", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotLike(String value) {
            addCriterion("LCC_CODE not like", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeIn(List<String> values) {
            addCriterion("LCC_CODE in", values, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotIn(List<String> values) {
            addCriterion("LCC_CODE not in", values, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeBetween(String value1, String value2) {
            addCriterion("LCC_CODE between", value1, value2, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotBetween(String value1, String value2) {
            addCriterion("LCC_CODE not between", value1, value2, "lccCode");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNull() {
            addCriterion("PATIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNotNull() {
            addCriterion("PATIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdEqualTo(String value) {
            addCriterion("PATIENT_ID =", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotEqualTo(String value) {
            addCriterion("PATIENT_ID <>", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThan(String value) {
            addCriterion("PATIENT_ID >", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_ID >=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThan(String value) {
            addCriterion("PATIENT_ID <", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_ID <=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLike(String value) {
            addCriterion("PATIENT_ID like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotLike(String value) {
            addCriterion("PATIENT_ID not like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIn(List<String> values) {
            addCriterion("PATIENT_ID in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotIn(List<String> values) {
            addCriterion("PATIENT_ID not in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdBetween(String value1, String value2) {
            addCriterion("PATIENT_ID between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotBetween(String value1, String value2) {
            addCriterion("PATIENT_ID not between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andEventCodeIsNull() {
            addCriterion("EVENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andEventCodeIsNotNull() {
            addCriterion("EVENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andEventCodeEqualTo(String value) {
            addCriterion("EVENT_CODE =", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotEqualTo(String value) {
            addCriterion("EVENT_CODE <>", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeGreaterThan(String value) {
            addCriterion("EVENT_CODE >", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_CODE >=", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeLessThan(String value) {
            addCriterion("EVENT_CODE <", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeLessThanOrEqualTo(String value) {
            addCriterion("EVENT_CODE <=", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeLike(String value) {
            addCriterion("EVENT_CODE like", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotLike(String value) {
            addCriterion("EVENT_CODE not like", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeIn(List<String> values) {
            addCriterion("EVENT_CODE in", values, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotIn(List<String> values) {
            addCriterion("EVENT_CODE not in", values, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeBetween(String value1, String value2) {
            addCriterion("EVENT_CODE between", value1, value2, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotBetween(String value1, String value2) {
            addCriterion("EVENT_CODE not between", value1, value2, "eventCode");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("PATIENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("PATIENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("PATIENT_NAME =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("PATIENT_NAME <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("PATIENT_NAME >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("PATIENT_NAME <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("PATIENT_NAME like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("PATIENT_NAME not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("PATIENT_NAME in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("PATIENT_NAME not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNull() {
            addCriterion("EVENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNotNull() {
            addCriterion("EVENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEventNameEqualTo(String value) {
            addCriterion("EVENT_NAME =", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotEqualTo(String value) {
            addCriterion("EVENT_NAME <>", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThan(String value) {
            addCriterion("EVENT_NAME >", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_NAME >=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThan(String value) {
            addCriterion("EVENT_NAME <", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThanOrEqualTo(String value) {
            addCriterion("EVENT_NAME <=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLike(String value) {
            addCriterion("EVENT_NAME like", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotLike(String value) {
            addCriterion("EVENT_NAME not like", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameIn(List<String> values) {
            addCriterion("EVENT_NAME in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotIn(List<String> values) {
            addCriterion("EVENT_NAME not in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameBetween(String value1, String value2) {
            addCriterion("EVENT_NAME between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotBetween(String value1, String value2) {
            addCriterion("EVENT_NAME not between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andInHosDateIsNull() {
            addCriterion("IN_HOS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInHosDateIsNotNull() {
            addCriterion("IN_HOS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInHosDateEqualTo(Date value) {
            addCriterionForJDBCDate("IN_HOS_DATE =", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("IN_HOS_DATE <>", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateGreaterThan(Date value) {
            addCriterionForJDBCDate("IN_HOS_DATE >", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("IN_HOS_DATE >=", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateLessThan(Date value) {
            addCriterionForJDBCDate("IN_HOS_DATE <", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("IN_HOS_DATE <=", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateIn(List<Date> values) {
            addCriterionForJDBCDate("IN_HOS_DATE in", values, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("IN_HOS_DATE not in", values, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("IN_HOS_DATE between", value1, value2, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("IN_HOS_DATE not between", value1, value2, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateIsNull() {
            addCriterion("OUT_HOS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOutHosDateIsNotNull() {
            addCriterion("OUT_HOS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOutHosDateEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_HOS_DATE =", value, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_HOS_DATE <>", value, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateGreaterThan(Date value) {
            addCriterionForJDBCDate("OUT_HOS_DATE >", value, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_HOS_DATE >=", value, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateLessThan(Date value) {
            addCriterionForJDBCDate("OUT_HOS_DATE <", value, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_HOS_DATE <=", value, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateIn(List<Date> values) {
            addCriterionForJDBCDate("OUT_HOS_DATE in", values, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("OUT_HOS_DATE not in", values, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OUT_HOS_DATE between", value1, value2, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andOutHosDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OUT_HOS_DATE not between", value1, value2, "outHosDate");
            return (Criteria) this;
        }

        public Criteria andHosNameIsNull() {
            addCriterion("HOS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHosNameIsNotNull() {
            addCriterion("HOS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHosNameEqualTo(String value) {
            addCriterion("HOS_NAME =", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameNotEqualTo(String value) {
            addCriterion("HOS_NAME <>", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameGreaterThan(String value) {
            addCriterion("HOS_NAME >", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOS_NAME >=", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameLessThan(String value) {
            addCriterion("HOS_NAME <", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameLessThanOrEqualTo(String value) {
            addCriterion("HOS_NAME <=", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameLike(String value) {
            addCriterion("HOS_NAME like", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameNotLike(String value) {
            addCriterion("HOS_NAME not like", value, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameIn(List<String> values) {
            addCriterion("HOS_NAME in", values, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameNotIn(List<String> values) {
            addCriterion("HOS_NAME not in", values, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameBetween(String value1, String value2) {
            addCriterion("HOS_NAME between", value1, value2, "hosName");
            return (Criteria) this;
        }

        public Criteria andHosNameNotBetween(String value1, String value2) {
            addCriterion("HOS_NAME not between", value1, value2, "hosName");
            return (Criteria) this;
        }

        public Criteria andEventDateIsNull() {
            addCriterion("EVENT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEventDateIsNotNull() {
            addCriterion("EVENT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEventDateEqualTo(Date value) {
            addCriterion("EVENT_DATE =", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotEqualTo(Date value) {
            addCriterion("EVENT_DATE <>", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateGreaterThan(Date value) {
            addCriterion("EVENT_DATE >", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EVENT_DATE >=", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLessThan(Date value) {
            addCriterion("EVENT_DATE <", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLessThanOrEqualTo(Date value) {
            addCriterion("EVENT_DATE <=", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateIn(List<Date> values) {
            addCriterion("EVENT_DATE in", values, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotIn(List<Date> values) {
            addCriterion("EVENT_DATE not in", values, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateBetween(Date value1, Date value2) {
            addCriterion("EVENT_DATE between", value1, value2, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotBetween(Date value1, Date value2) {
            addCriterion("EVENT_DATE not between", value1, value2, "eventDate");
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

        public Criteria andIsDeathIsNull() {
            addCriterion("IS_DEATH is null");
            return (Criteria) this;
        }

        public Criteria andIsDeathIsNotNull() {
            addCriterion("IS_DEATH is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeathEqualTo(String value) {
            addCriterion("IS_DEATH =", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathNotEqualTo(String value) {
            addCriterion("IS_DEATH <>", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathGreaterThan(String value) {
            addCriterion("IS_DEATH >", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DEATH >=", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathLessThan(String value) {
            addCriterion("IS_DEATH <", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathLessThanOrEqualTo(String value) {
            addCriterion("IS_DEATH <=", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathLike(String value) {
            addCriterion("IS_DEATH like", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathNotLike(String value) {
            addCriterion("IS_DEATH not like", value, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathIn(List<String> values) {
            addCriterion("IS_DEATH in", values, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathNotIn(List<String> values) {
            addCriterion("IS_DEATH not in", values, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathBetween(String value1, String value2) {
            addCriterion("IS_DEATH between", value1, value2, "isDeath");
            return (Criteria) this;
        }

        public Criteria andIsDeathNotBetween(String value1, String value2) {
            addCriterion("IS_DEATH not between", value1, value2, "isDeath");
            return (Criteria) this;
        }

        public Criteria andEventTypeIsNull() {
            addCriterion("EVENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEventTypeIsNotNull() {
            addCriterion("EVENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEventTypeEqualTo(String value) {
            addCriterion("EVENT_TYPE =", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotEqualTo(String value) {
            addCriterion("EVENT_TYPE <>", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeGreaterThan(String value) {
            addCriterion("EVENT_TYPE >", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_TYPE >=", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeLessThan(String value) {
            addCriterion("EVENT_TYPE <", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeLessThanOrEqualTo(String value) {
            addCriterion("EVENT_TYPE <=", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeLike(String value) {
            addCriterion("EVENT_TYPE like", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotLike(String value) {
            addCriterion("EVENT_TYPE not like", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeIn(List<String> values) {
            addCriterion("EVENT_TYPE in", values, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotIn(List<String> values) {
            addCriterion("EVENT_TYPE not in", values, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeBetween(String value1, String value2) {
            addCriterion("EVENT_TYPE between", value1, value2, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotBetween(String value1, String value2) {
            addCriterion("EVENT_TYPE not between", value1, value2, "eventType");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Short value) {
            addCriterion("IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Short value) {
            addCriterion("IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Short value) {
            addCriterion("IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Short value) {
            addCriterion("IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Short value) {
            addCriterion("IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Short> values) {
            addCriterion("IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Short> values) {
            addCriterion("IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Short value1, Short value2) {
            addCriterion("IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Short value1, Short value2) {
            addCriterion("IS_DELETE not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andLogMinIdIsNull() {
            addCriterion("LOG_MIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogMinIdIsNotNull() {
            addCriterion("LOG_MIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogMinIdEqualTo(String value) {
            addCriterion("LOG_MIN_ID =", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdNotEqualTo(String value) {
            addCriterion("LOG_MIN_ID <>", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdGreaterThan(String value) {
            addCriterion("LOG_MIN_ID >", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_MIN_ID >=", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdLessThan(String value) {
            addCriterion("LOG_MIN_ID <", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdLessThanOrEqualTo(String value) {
            addCriterion("LOG_MIN_ID <=", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdLike(String value) {
            addCriterion("LOG_MIN_ID like", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdNotLike(String value) {
            addCriterion("LOG_MIN_ID not like", value, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdIn(List<String> values) {
            addCriterion("LOG_MIN_ID in", values, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdNotIn(List<String> values) {
            addCriterion("LOG_MIN_ID not in", values, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdBetween(String value1, String value2) {
            addCriterion("LOG_MIN_ID between", value1, value2, "logMinId");
            return (Criteria) this;
        }

        public Criteria andLogMinIdNotBetween(String value1, String value2) {
            addCriterion("LOG_MIN_ID not between", value1, value2, "logMinId");
            return (Criteria) this;
        }

        public Criteria andUqsNoIsNull() {
            addCriterion("UQS_NO is null");
            return (Criteria) this;
        }

        public Criteria andUqsNoIsNotNull() {
            addCriterion("UQS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andUqsNoEqualTo(String value) {
            addCriterion("UQS_NO =", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoNotEqualTo(String value) {
            addCriterion("UQS_NO <>", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoGreaterThan(String value) {
            addCriterion("UQS_NO >", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoGreaterThanOrEqualTo(String value) {
            addCriterion("UQS_NO >=", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoLessThan(String value) {
            addCriterion("UQS_NO <", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoLessThanOrEqualTo(String value) {
            addCriterion("UQS_NO <=", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoLike(String value) {
            addCriterion("UQS_NO like", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoNotLike(String value) {
            addCriterion("UQS_NO not like", value, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoIn(List<String> values) {
            addCriterion("UQS_NO in", values, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoNotIn(List<String> values) {
            addCriterion("UQS_NO not in", values, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoBetween(String value1, String value2) {
            addCriterion("UQS_NO between", value1, value2, "uqsNo");
            return (Criteria) this;
        }

        public Criteria andUqsNoNotBetween(String value1, String value2) {
            addCriterion("UQS_NO not between", value1, value2, "uqsNo");
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
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Short value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Short value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Short value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Short value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Short value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Short> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Short> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Short value1, Short value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Short value1, Short value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
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