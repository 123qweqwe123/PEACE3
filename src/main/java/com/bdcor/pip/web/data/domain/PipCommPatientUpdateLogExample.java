package com.bdcor.pip.web.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PipCommPatientUpdateLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public PipCommPatientUpdateLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
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

        public Criteria andIdNumberIsNull() {
            addCriterion("ID_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("ID_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("ID_NUMBER =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("ID_NUMBER <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("ID_NUMBER >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NUMBER >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("ID_NUMBER <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("ID_NUMBER <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("ID_NUMBER like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("ID_NUMBER not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("ID_NUMBER in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("ID_NUMBER not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("ID_NUMBER between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("ID_NUMBER not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andPatientIdsIsNull() {
            addCriterion("PATIENT_IDS is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdsIsNotNull() {
            addCriterion("PATIENT_IDS is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdsEqualTo(String value) {
            addCriterion("PATIENT_IDS =", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsNotEqualTo(String value) {
            addCriterion("PATIENT_IDS <>", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsGreaterThan(String value) {
            addCriterion("PATIENT_IDS >", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_IDS >=", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsLessThan(String value) {
            addCriterion("PATIENT_IDS <", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_IDS <=", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsLike(String value) {
            addCriterion("PATIENT_IDS like", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsNotLike(String value) {
            addCriterion("PATIENT_IDS not like", value, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsIn(List<String> values) {
            addCriterion("PATIENT_IDS in", values, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsNotIn(List<String> values) {
            addCriterion("PATIENT_IDS not in", values, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsBetween(String value1, String value2) {
            addCriterion("PATIENT_IDS between", value1, value2, "patientIds");
            return (Criteria) this;
        }

        public Criteria andPatientIdsNotBetween(String value1, String value2) {
            addCriterion("PATIENT_IDS not between", value1, value2, "patientIds");
            return (Criteria) this;
        }

        public Criteria andRiskCodesIsNull() {
            addCriterion("RISK_CODES is null");
            return (Criteria) this;
        }

        public Criteria andRiskCodesIsNotNull() {
            addCriterion("RISK_CODES is not null");
            return (Criteria) this;
        }

        public Criteria andRiskCodesEqualTo(String value) {
            addCriterion("RISK_CODES =", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesNotEqualTo(String value) {
            addCriterion("RISK_CODES <>", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesGreaterThan(String value) {
            addCriterion("RISK_CODES >", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_CODES >=", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesLessThan(String value) {
            addCriterion("RISK_CODES <", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesLessThanOrEqualTo(String value) {
            addCriterion("RISK_CODES <=", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesLike(String value) {
            addCriterion("RISK_CODES like", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesNotLike(String value) {
            addCriterion("RISK_CODES not like", value, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesIn(List<String> values) {
            addCriterion("RISK_CODES in", values, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesNotIn(List<String> values) {
            addCriterion("RISK_CODES not in", values, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesBetween(String value1, String value2) {
            addCriterion("RISK_CODES between", value1, value2, "riskCodes");
            return (Criteria) this;
        }

        public Criteria andRiskCodesNotBetween(String value1, String value2) {
            addCriterion("RISK_CODES not between", value1, value2, "riskCodes");
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

        public Criteria andNewPatientIdIsNull() {
            addCriterion("NEW_PATIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdIsNotNull() {
            addCriterion("NEW_PATIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdEqualTo(String value) {
            addCriterion("NEW_PATIENT_ID =", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdNotEqualTo(String value) {
            addCriterion("NEW_PATIENT_ID <>", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdGreaterThan(String value) {
            addCriterion("NEW_PATIENT_ID >", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdGreaterThanOrEqualTo(String value) {
            addCriterion("NEW_PATIENT_ID >=", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdLessThan(String value) {
            addCriterion("NEW_PATIENT_ID <", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdLessThanOrEqualTo(String value) {
            addCriterion("NEW_PATIENT_ID <=", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdLike(String value) {
            addCriterion("NEW_PATIENT_ID like", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdNotLike(String value) {
            addCriterion("NEW_PATIENT_ID not like", value, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdIn(List<String> values) {
            addCriterion("NEW_PATIENT_ID in", values, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdNotIn(List<String> values) {
            addCriterion("NEW_PATIENT_ID not in", values, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdBetween(String value1, String value2) {
            addCriterion("NEW_PATIENT_ID between", value1, value2, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andNewPatientIdNotBetween(String value1, String value2) {
            addCriterion("NEW_PATIENT_ID not between", value1, value2, "newPatientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeIsNull() {
            addCriterion("PATIENT_ID_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeIsNotNull() {
            addCriterion("PATIENT_ID_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeEqualTo(String value) {
            addCriterion("PATIENT_ID_TYPE =", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeNotEqualTo(String value) {
            addCriterion("PATIENT_ID_TYPE <>", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeGreaterThan(String value) {
            addCriterion("PATIENT_ID_TYPE >", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_ID_TYPE >=", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeLessThan(String value) {
            addCriterion("PATIENT_ID_TYPE <", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_ID_TYPE <=", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeLike(String value) {
            addCriterion("PATIENT_ID_TYPE like", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeNotLike(String value) {
            addCriterion("PATIENT_ID_TYPE not like", value, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeIn(List<String> values) {
            addCriterion("PATIENT_ID_TYPE in", values, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeNotIn(List<String> values) {
            addCriterion("PATIENT_ID_TYPE not in", values, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeBetween(String value1, String value2) {
            addCriterion("PATIENT_ID_TYPE between", value1, value2, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andPatientIdTypeNotBetween(String value1, String value2) {
            addCriterion("PATIENT_ID_TYPE not between", value1, value2, "patientIdType");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeIsNull() {
            addCriterion("NEW_RISK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeIsNotNull() {
            addCriterion("NEW_RISK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeEqualTo(String value) {
            addCriterion("NEW_RISK_CODE =", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeNotEqualTo(String value) {
            addCriterion("NEW_RISK_CODE <>", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeGreaterThan(String value) {
            addCriterion("NEW_RISK_CODE >", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeGreaterThanOrEqualTo(String value) {
            addCriterion("NEW_RISK_CODE >=", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeLessThan(String value) {
            addCriterion("NEW_RISK_CODE <", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeLessThanOrEqualTo(String value) {
            addCriterion("NEW_RISK_CODE <=", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeLike(String value) {
            addCriterion("NEW_RISK_CODE like", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeNotLike(String value) {
            addCriterion("NEW_RISK_CODE not like", value, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeIn(List<String> values) {
            addCriterion("NEW_RISK_CODE in", values, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeNotIn(List<String> values) {
            addCriterion("NEW_RISK_CODE not in", values, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeBetween(String value1, String value2) {
            addCriterion("NEW_RISK_CODE between", value1, value2, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andNewRiskCodeNotBetween(String value1, String value2) {
            addCriterion("NEW_RISK_CODE not between", value1, value2, "newRiskCode");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeIsNull() {
            addCriterion("RISK_CODE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeIsNotNull() {
            addCriterion("RISK_CODE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeEqualTo(String value) {
            addCriterion("RISK_CODE_TYPE =", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeNotEqualTo(String value) {
            addCriterion("RISK_CODE_TYPE <>", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeGreaterThan(String value) {
            addCriterion("RISK_CODE_TYPE >", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RISK_CODE_TYPE >=", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeLessThan(String value) {
            addCriterion("RISK_CODE_TYPE <", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("RISK_CODE_TYPE <=", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeLike(String value) {
            addCriterion("RISK_CODE_TYPE like", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeNotLike(String value) {
            addCriterion("RISK_CODE_TYPE not like", value, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeIn(List<String> values) {
            addCriterion("RISK_CODE_TYPE in", values, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeNotIn(List<String> values) {
            addCriterion("RISK_CODE_TYPE not in", values, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeBetween(String value1, String value2) {
            addCriterion("RISK_CODE_TYPE between", value1, value2, "riskCodeType");
            return (Criteria) this;
        }

        public Criteria andRiskCodeTypeNotBetween(String value1, String value2) {
            addCriterion("RISK_CODE_TYPE not between", value1, value2, "riskCodeType");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PIP_COMM_PATIENT_UPDATE_LOG
     *
     * @mbggenerated
     */
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