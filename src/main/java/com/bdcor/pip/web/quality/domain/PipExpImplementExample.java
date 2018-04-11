package com.bdcor.pip.web.quality.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PipExpImplementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipExpImplementExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andLccNameIsNull() {
            addCriterion("LCC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLccNameIsNotNull() {
            addCriterion("LCC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLccNameEqualTo(String value) {
            addCriterion("LCC_NAME =", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameNotEqualTo(String value) {
            addCriterion("LCC_NAME <>", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameGreaterThan(String value) {
            addCriterion("LCC_NAME >", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameGreaterThanOrEqualTo(String value) {
            addCriterion("LCC_NAME >=", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameLessThan(String value) {
            addCriterion("LCC_NAME <", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameLessThanOrEqualTo(String value) {
            addCriterion("LCC_NAME <=", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameLike(String value) {
            addCriterion("LCC_NAME like", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameNotLike(String value) {
            addCriterion("LCC_NAME not like", value, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameIn(List<String> values) {
            addCriterion("LCC_NAME in", values, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameNotIn(List<String> values) {
            addCriterion("LCC_NAME not in", values, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameBetween(String value1, String value2) {
            addCriterion("LCC_NAME between", value1, value2, "lccName");
            return (Criteria) this;
        }

        public Criteria andLccNameNotBetween(String value1, String value2) {
            addCriterion("LCC_NAME not between", value1, value2, "lccName");
            return (Criteria) this;
        }

        public Criteria andImplementDateIsNull() {
            addCriterion("IMPLEMENT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andImplementDateIsNotNull() {
            addCriterion("IMPLEMENT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andImplementDateEqualTo(Date value) {
            addCriterion("IMPLEMENT_DATE =", value, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateNotEqualTo(Date value) {
            addCriterion("IMPLEMENT_DATE <>", value, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateGreaterThan(Date value) {
            addCriterion("IMPLEMENT_DATE >", value, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateGreaterThanOrEqualTo(Date value) {
            addCriterion("IMPLEMENT_DATE >=", value, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateLessThan(Date value) {
            addCriterion("IMPLEMENT_DATE <", value, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateLessThanOrEqualTo(Date value) {
            addCriterion("IMPLEMENT_DATE <=", value, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateIn(List<Date> values) {
            addCriterion("IMPLEMENT_DATE in", values, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateNotIn(List<Date> values) {
            addCriterion("IMPLEMENT_DATE not in", values, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateBetween(Date value1, Date value2) {
            addCriterion("IMPLEMENT_DATE between", value1, value2, "implementDate");
            return (Criteria) this;
        }

        public Criteria andImplementDateNotBetween(Date value1, Date value2) {
            addCriterion("IMPLEMENT_DATE not between", value1, value2, "implementDate");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeIsNull() {
            addCriterion("WATCH_PATIENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeIsNotNull() {
            addCriterion("WATCH_PATIENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeEqualTo(String value) {
            addCriterion("WATCH_PATIENT_TYPE =", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeNotEqualTo(String value) {
            addCriterion("WATCH_PATIENT_TYPE <>", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeGreaterThan(String value) {
            addCriterion("WATCH_PATIENT_TYPE >", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeGreaterThanOrEqualTo(String value) {
            addCriterion("WATCH_PATIENT_TYPE >=", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeLessThan(String value) {
            addCriterion("WATCH_PATIENT_TYPE <", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeLessThanOrEqualTo(String value) {
            addCriterion("WATCH_PATIENT_TYPE <=", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeLike(String value) {
            addCriterion("WATCH_PATIENT_TYPE like", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeNotLike(String value) {
            addCriterion("WATCH_PATIENT_TYPE not like", value, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeIn(List<String> values) {
            addCriterion("WATCH_PATIENT_TYPE in", values, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeNotIn(List<String> values) {
            addCriterion("WATCH_PATIENT_TYPE not in", values, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeBetween(String value1, String value2) {
            addCriterion("WATCH_PATIENT_TYPE between", value1, value2, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andWatchPatientTypeNotBetween(String value1, String value2) {
            addCriterion("WATCH_PATIENT_TYPE not between", value1, value2, "watchPatientType");
            return (Criteria) this;
        }

        public Criteria andPatientCountIsNull() {
            addCriterion("PATIENT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andPatientCountIsNotNull() {
            addCriterion("PATIENT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPatientCountEqualTo(Short value) {
            addCriterion("PATIENT_COUNT =", value, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountNotEqualTo(Short value) {
            addCriterion("PATIENT_COUNT <>", value, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountGreaterThan(Short value) {
            addCriterion("PATIENT_COUNT >", value, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountGreaterThanOrEqualTo(Short value) {
            addCriterion("PATIENT_COUNT >=", value, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountLessThan(Short value) {
            addCriterion("PATIENT_COUNT <", value, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountLessThanOrEqualTo(Short value) {
            addCriterion("PATIENT_COUNT <=", value, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountIn(List<Short> values) {
            addCriterion("PATIENT_COUNT in", values, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountNotIn(List<Short> values) {
            addCriterion("PATIENT_COUNT not in", values, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountBetween(Short value1, Short value2) {
            addCriterion("PATIENT_COUNT between", value1, value2, "patientCount");
            return (Criteria) this;
        }

        public Criteria andPatientCountNotBetween(Short value1, Short value2) {
            addCriterion("PATIENT_COUNT not between", value1, value2, "patientCount");
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
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
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

        public Criteria andProvinceCodeIsNull() {
            addCriterion("PROVINCE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("PROVINCE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(String value) {
            addCriterion("PROVINCE_CODE =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(String value) {
            addCriterion("PROVINCE_CODE <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(String value) {
            addCriterion("PROVINCE_CODE >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_CODE >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(String value) {
            addCriterion("PROVINCE_CODE <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_CODE <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLike(String value) {
            addCriterion("PROVINCE_CODE like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotLike(String value) {
            addCriterion("PROVINCE_CODE not like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<String> values) {
            addCriterion("PROVINCE_CODE in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<String> values) {
            addCriterion("PROVINCE_CODE not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(String value1, String value2) {
            addCriterion("PROVINCE_CODE between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_CODE not between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andIsztqk1IsNull() {
            addCriterion("ISZTQK_1 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk1IsNotNull() {
            addCriterion("ISZTQK_1 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk1EqualTo(String value) {
            addCriterion("ISZTQK_1 =", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1NotEqualTo(String value) {
            addCriterion("ISZTQK_1 <>", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1GreaterThan(String value) {
            addCriterion("ISZTQK_1 >", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_1 >=", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1LessThan(String value) {
            addCriterion("ISZTQK_1 <", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_1 <=", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1Like(String value) {
            addCriterion("ISZTQK_1 like", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1NotLike(String value) {
            addCriterion("ISZTQK_1 not like", value, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1In(List<String> values) {
            addCriterion("ISZTQK_1 in", values, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1NotIn(List<String> values) {
            addCriterion("ISZTQK_1 not in", values, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1Between(String value1, String value2) {
            addCriterion("ISZTQK_1 between", value1, value2, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk1NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_1 not between", value1, value2, "isztqk1");
            return (Criteria) this;
        }

        public Criteria andIsztqk2IsNull() {
            addCriterion("ISZTQK_2 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk2IsNotNull() {
            addCriterion("ISZTQK_2 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk2EqualTo(String value) {
            addCriterion("ISZTQK_2 =", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2NotEqualTo(String value) {
            addCriterion("ISZTQK_2 <>", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2GreaterThan(String value) {
            addCriterion("ISZTQK_2 >", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_2 >=", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2LessThan(String value) {
            addCriterion("ISZTQK_2 <", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_2 <=", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2Like(String value) {
            addCriterion("ISZTQK_2 like", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2NotLike(String value) {
            addCriterion("ISZTQK_2 not like", value, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2In(List<String> values) {
            addCriterion("ISZTQK_2 in", values, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2NotIn(List<String> values) {
            addCriterion("ISZTQK_2 not in", values, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2Between(String value1, String value2) {
            addCriterion("ISZTQK_2 between", value1, value2, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk2NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_2 not between", value1, value2, "isztqk2");
            return (Criteria) this;
        }

        public Criteria andIsztqk3IsNull() {
            addCriterion("ISZTQK_3 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk3IsNotNull() {
            addCriterion("ISZTQK_3 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk3EqualTo(String value) {
            addCriterion("ISZTQK_3 =", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3NotEqualTo(String value) {
            addCriterion("ISZTQK_3 <>", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3GreaterThan(String value) {
            addCriterion("ISZTQK_3 >", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_3 >=", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3LessThan(String value) {
            addCriterion("ISZTQK_3 <", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_3 <=", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3Like(String value) {
            addCriterion("ISZTQK_3 like", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3NotLike(String value) {
            addCriterion("ISZTQK_3 not like", value, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3In(List<String> values) {
            addCriterion("ISZTQK_3 in", values, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3NotIn(List<String> values) {
            addCriterion("ISZTQK_3 not in", values, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3Between(String value1, String value2) {
            addCriterion("ISZTQK_3 between", value1, value2, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk3NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_3 not between", value1, value2, "isztqk3");
            return (Criteria) this;
        }

        public Criteria andIsztqk4IsNull() {
            addCriterion("ISZTQK_4 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk4IsNotNull() {
            addCriterion("ISZTQK_4 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk4EqualTo(String value) {
            addCriterion("ISZTQK_4 =", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4NotEqualTo(String value) {
            addCriterion("ISZTQK_4 <>", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4GreaterThan(String value) {
            addCriterion("ISZTQK_4 >", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_4 >=", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4LessThan(String value) {
            addCriterion("ISZTQK_4 <", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_4 <=", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4Like(String value) {
            addCriterion("ISZTQK_4 like", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4NotLike(String value) {
            addCriterion("ISZTQK_4 not like", value, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4In(List<String> values) {
            addCriterion("ISZTQK_4 in", values, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4NotIn(List<String> values) {
            addCriterion("ISZTQK_4 not in", values, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4Between(String value1, String value2) {
            addCriterion("ISZTQK_4 between", value1, value2, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk4NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_4 not between", value1, value2, "isztqk4");
            return (Criteria) this;
        }

        public Criteria andIsztqk5IsNull() {
            addCriterion("ISZTQK_5 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk5IsNotNull() {
            addCriterion("ISZTQK_5 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk5EqualTo(String value) {
            addCriterion("ISZTQK_5 =", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5NotEqualTo(String value) {
            addCriterion("ISZTQK_5 <>", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5GreaterThan(String value) {
            addCriterion("ISZTQK_5 >", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_5 >=", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5LessThan(String value) {
            addCriterion("ISZTQK_5 <", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_5 <=", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5Like(String value) {
            addCriterion("ISZTQK_5 like", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5NotLike(String value) {
            addCriterion("ISZTQK_5 not like", value, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5In(List<String> values) {
            addCriterion("ISZTQK_5 in", values, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5NotIn(List<String> values) {
            addCriterion("ISZTQK_5 not in", values, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5Between(String value1, String value2) {
            addCriterion("ISZTQK_5 between", value1, value2, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk5NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_5 not between", value1, value2, "isztqk5");
            return (Criteria) this;
        }

        public Criteria andIsztqk6IsNull() {
            addCriterion("ISZTQK_6 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk6IsNotNull() {
            addCriterion("ISZTQK_6 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk6EqualTo(String value) {
            addCriterion("ISZTQK_6 =", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6NotEqualTo(String value) {
            addCriterion("ISZTQK_6 <>", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6GreaterThan(String value) {
            addCriterion("ISZTQK_6 >", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_6 >=", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6LessThan(String value) {
            addCriterion("ISZTQK_6 <", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_6 <=", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6Like(String value) {
            addCriterion("ISZTQK_6 like", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6NotLike(String value) {
            addCriterion("ISZTQK_6 not like", value, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6In(List<String> values) {
            addCriterion("ISZTQK_6 in", values, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6NotIn(List<String> values) {
            addCriterion("ISZTQK_6 not in", values, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6Between(String value1, String value2) {
            addCriterion("ISZTQK_6 between", value1, value2, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk6NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_6 not between", value1, value2, "isztqk6");
            return (Criteria) this;
        }

        public Criteria andIsztqk7IsNull() {
            addCriterion("ISZTQK_7 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk7IsNotNull() {
            addCriterion("ISZTQK_7 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk7EqualTo(String value) {
            addCriterion("ISZTQK_7 =", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7NotEqualTo(String value) {
            addCriterion("ISZTQK_7 <>", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7GreaterThan(String value) {
            addCriterion("ISZTQK_7 >", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_7 >=", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7LessThan(String value) {
            addCriterion("ISZTQK_7 <", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_7 <=", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7Like(String value) {
            addCriterion("ISZTQK_7 like", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7NotLike(String value) {
            addCriterion("ISZTQK_7 not like", value, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7In(List<String> values) {
            addCriterion("ISZTQK_7 in", values, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7NotIn(List<String> values) {
            addCriterion("ISZTQK_7 not in", values, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7Between(String value1, String value2) {
            addCriterion("ISZTQK_7 between", value1, value2, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk7NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_7 not between", value1, value2, "isztqk7");
            return (Criteria) this;
        }

        public Criteria andIsztqk8IsNull() {
            addCriterion("ISZTQK_8 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk8IsNotNull() {
            addCriterion("ISZTQK_8 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk8EqualTo(String value) {
            addCriterion("ISZTQK_8 =", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8NotEqualTo(String value) {
            addCriterion("ISZTQK_8 <>", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8GreaterThan(String value) {
            addCriterion("ISZTQK_8 >", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_8 >=", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8LessThan(String value) {
            addCriterion("ISZTQK_8 <", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_8 <=", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8Like(String value) {
            addCriterion("ISZTQK_8 like", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8NotLike(String value) {
            addCriterion("ISZTQK_8 not like", value, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8In(List<String> values) {
            addCriterion("ISZTQK_8 in", values, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8NotIn(List<String> values) {
            addCriterion("ISZTQK_8 not in", values, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8Between(String value1, String value2) {
            addCriterion("ISZTQK_8 between", value1, value2, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk8NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_8 not between", value1, value2, "isztqk8");
            return (Criteria) this;
        }

        public Criteria andIsztqk9IsNull() {
            addCriterion("ISZTQK_9 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk9IsNotNull() {
            addCriterion("ISZTQK_9 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk9EqualTo(String value) {
            addCriterion("ISZTQK_9 =", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9NotEqualTo(String value) {
            addCriterion("ISZTQK_9 <>", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9GreaterThan(String value) {
            addCriterion("ISZTQK_9 >", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_9 >=", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9LessThan(String value) {
            addCriterion("ISZTQK_9 <", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_9 <=", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9Like(String value) {
            addCriterion("ISZTQK_9 like", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9NotLike(String value) {
            addCriterion("ISZTQK_9 not like", value, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9In(List<String> values) {
            addCriterion("ISZTQK_9 in", values, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9NotIn(List<String> values) {
            addCriterion("ISZTQK_9 not in", values, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9Between(String value1, String value2) {
            addCriterion("ISZTQK_9 between", value1, value2, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk9NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_9 not between", value1, value2, "isztqk9");
            return (Criteria) this;
        }

        public Criteria andIsztqk10IsNull() {
            addCriterion("ISZTQK_10 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk10IsNotNull() {
            addCriterion("ISZTQK_10 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk10EqualTo(String value) {
            addCriterion("ISZTQK_10 =", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10NotEqualTo(String value) {
            addCriterion("ISZTQK_10 <>", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10GreaterThan(String value) {
            addCriterion("ISZTQK_10 >", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_10 >=", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10LessThan(String value) {
            addCriterion("ISZTQK_10 <", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_10 <=", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10Like(String value) {
            addCriterion("ISZTQK_10 like", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10NotLike(String value) {
            addCriterion("ISZTQK_10 not like", value, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10In(List<String> values) {
            addCriterion("ISZTQK_10 in", values, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10NotIn(List<String> values) {
            addCriterion("ISZTQK_10 not in", values, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10Between(String value1, String value2) {
            addCriterion("ISZTQK_10 between", value1, value2, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk10NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_10 not between", value1, value2, "isztqk10");
            return (Criteria) this;
        }

        public Criteria andIsztqk11IsNull() {
            addCriterion("ISZTQK_11 is null");
            return (Criteria) this;
        }

        public Criteria andIsztqk11IsNotNull() {
            addCriterion("ISZTQK_11 is not null");
            return (Criteria) this;
        }

        public Criteria andIsztqk11EqualTo(String value) {
            addCriterion("ISZTQK_11 =", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11NotEqualTo(String value) {
            addCriterion("ISZTQK_11 <>", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11GreaterThan(String value) {
            addCriterion("ISZTQK_11 >", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11GreaterThanOrEqualTo(String value) {
            addCriterion("ISZTQK_11 >=", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11LessThan(String value) {
            addCriterion("ISZTQK_11 <", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11LessThanOrEqualTo(String value) {
            addCriterion("ISZTQK_11 <=", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11Like(String value) {
            addCriterion("ISZTQK_11 like", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11NotLike(String value) {
            addCriterion("ISZTQK_11 not like", value, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11In(List<String> values) {
            addCriterion("ISZTQK_11 in", values, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11NotIn(List<String> values) {
            addCriterion("ISZTQK_11 not in", values, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11Between(String value1, String value2) {
            addCriterion("ISZTQK_11 between", value1, value2, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsztqk11NotBetween(String value1, String value2) {
            addCriterion("ISZTQK_11 not between", value1, value2, "isztqk11");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1IsNull() {
            addCriterion("ISYJWJHC_1 is null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1IsNotNull() {
            addCriterion("ISYJWJHC_1 is not null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1EqualTo(String value) {
            addCriterion("ISYJWJHC_1 =", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1NotEqualTo(String value) {
            addCriterion("ISYJWJHC_1 <>", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1GreaterThan(String value) {
            addCriterion("ISYJWJHC_1 >", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1GreaterThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_1 >=", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1LessThan(String value) {
            addCriterion("ISYJWJHC_1 <", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1LessThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_1 <=", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1Like(String value) {
            addCriterion("ISYJWJHC_1 like", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1NotLike(String value) {
            addCriterion("ISYJWJHC_1 not like", value, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1In(List<String> values) {
            addCriterion("ISYJWJHC_1 in", values, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1NotIn(List<String> values) {
            addCriterion("ISYJWJHC_1 not in", values, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1Between(String value1, String value2) {
            addCriterion("ISYJWJHC_1 between", value1, value2, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc1NotBetween(String value1, String value2) {
            addCriterion("ISYJWJHC_1 not between", value1, value2, "isyjwjhc1");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2IsNull() {
            addCriterion("ISYJWJHC_2 is null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2IsNotNull() {
            addCriterion("ISYJWJHC_2 is not null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2EqualTo(String value) {
            addCriterion("ISYJWJHC_2 =", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2NotEqualTo(String value) {
            addCriterion("ISYJWJHC_2 <>", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2GreaterThan(String value) {
            addCriterion("ISYJWJHC_2 >", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2GreaterThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_2 >=", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2LessThan(String value) {
            addCriterion("ISYJWJHC_2 <", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2LessThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_2 <=", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2Like(String value) {
            addCriterion("ISYJWJHC_2 like", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2NotLike(String value) {
            addCriterion("ISYJWJHC_2 not like", value, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2In(List<String> values) {
            addCriterion("ISYJWJHC_2 in", values, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2NotIn(List<String> values) {
            addCriterion("ISYJWJHC_2 not in", values, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2Between(String value1, String value2) {
            addCriterion("ISYJWJHC_2 between", value1, value2, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc2NotBetween(String value1, String value2) {
            addCriterion("ISYJWJHC_2 not between", value1, value2, "isyjwjhc2");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3IsNull() {
            addCriterion("ISYJWJHC_3 is null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3IsNotNull() {
            addCriterion("ISYJWJHC_3 is not null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3EqualTo(String value) {
            addCriterion("ISYJWJHC_3 =", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3NotEqualTo(String value) {
            addCriterion("ISYJWJHC_3 <>", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3GreaterThan(String value) {
            addCriterion("ISYJWJHC_3 >", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3GreaterThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_3 >=", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3LessThan(String value) {
            addCriterion("ISYJWJHC_3 <", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3LessThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_3 <=", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3Like(String value) {
            addCriterion("ISYJWJHC_3 like", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3NotLike(String value) {
            addCriterion("ISYJWJHC_3 not like", value, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3In(List<String> values) {
            addCriterion("ISYJWJHC_3 in", values, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3NotIn(List<String> values) {
            addCriterion("ISYJWJHC_3 not in", values, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3Between(String value1, String value2) {
            addCriterion("ISYJWJHC_3 between", value1, value2, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc3NotBetween(String value1, String value2) {
            addCriterion("ISYJWJHC_3 not between", value1, value2, "isyjwjhc3");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4IsNull() {
            addCriterion("ISYJWJHC_4 is null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4IsNotNull() {
            addCriterion("ISYJWJHC_4 is not null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4EqualTo(String value) {
            addCriterion("ISYJWJHC_4 =", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4NotEqualTo(String value) {
            addCriterion("ISYJWJHC_4 <>", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4GreaterThan(String value) {
            addCriterion("ISYJWJHC_4 >", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4GreaterThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_4 >=", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4LessThan(String value) {
            addCriterion("ISYJWJHC_4 <", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4LessThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_4 <=", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4Like(String value) {
            addCriterion("ISYJWJHC_4 like", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4NotLike(String value) {
            addCriterion("ISYJWJHC_4 not like", value, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4In(List<String> values) {
            addCriterion("ISYJWJHC_4 in", values, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4NotIn(List<String> values) {
            addCriterion("ISYJWJHC_4 not in", values, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4Between(String value1, String value2) {
            addCriterion("ISYJWJHC_4 between", value1, value2, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc4NotBetween(String value1, String value2) {
            addCriterion("ISYJWJHC_4 not between", value1, value2, "isyjwjhc4");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5IsNull() {
            addCriterion("ISYJWJHC_5 is null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5IsNotNull() {
            addCriterion("ISYJWJHC_5 is not null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5EqualTo(String value) {
            addCriterion("ISYJWJHC_5 =", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5NotEqualTo(String value) {
            addCriterion("ISYJWJHC_5 <>", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5GreaterThan(String value) {
            addCriterion("ISYJWJHC_5 >", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5GreaterThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_5 >=", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5LessThan(String value) {
            addCriterion("ISYJWJHC_5 <", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5LessThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_5 <=", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5Like(String value) {
            addCriterion("ISYJWJHC_5 like", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5NotLike(String value) {
            addCriterion("ISYJWJHC_5 not like", value, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5In(List<String> values) {
            addCriterion("ISYJWJHC_5 in", values, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5NotIn(List<String> values) {
            addCriterion("ISYJWJHC_5 not in", values, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5Between(String value1, String value2) {
            addCriterion("ISYJWJHC_5 between", value1, value2, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc5NotBetween(String value1, String value2) {
            addCriterion("ISYJWJHC_5 not between", value1, value2, "isyjwjhc5");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6IsNull() {
            addCriterion("ISYJWJHC_6 is null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6IsNotNull() {
            addCriterion("ISYJWJHC_6 is not null");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6EqualTo(String value) {
            addCriterion("ISYJWJHC_6 =", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6NotEqualTo(String value) {
            addCriterion("ISYJWJHC_6 <>", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6GreaterThan(String value) {
            addCriterion("ISYJWJHC_6 >", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6GreaterThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_6 >=", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6LessThan(String value) {
            addCriterion("ISYJWJHC_6 <", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6LessThanOrEqualTo(String value) {
            addCriterion("ISYJWJHC_6 <=", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6Like(String value) {
            addCriterion("ISYJWJHC_6 like", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6NotLike(String value) {
            addCriterion("ISYJWJHC_6 not like", value, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6In(List<String> values) {
            addCriterion("ISYJWJHC_6 in", values, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6NotIn(List<String> values) {
            addCriterion("ISYJWJHC_6 not in", values, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6Between(String value1, String value2) {
            addCriterion("ISYJWJHC_6 between", value1, value2, "isyjwjhc6");
            return (Criteria) this;
        }

        public Criteria andIsyjwjhc6NotBetween(String value1, String value2) {
            addCriterion("ISYJWJHC_6 not between", value1, value2, "isyjwjhc6");
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