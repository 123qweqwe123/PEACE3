package com.bdcor.pip.web.material.supp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PipMmsScmarchivesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipMmsScmarchivesExample() {
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

        public Criteria andIdEqualTo(Short value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Short value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Short value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Short value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Short value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Short value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Short> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Short> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Short value1, Short value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Short value1, Short value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andArchivesNoIsNull() {
            addCriterion("ARCHIVES_NO is null");
            return (Criteria) this;
        }

        public Criteria andArchivesNoIsNotNull() {
            addCriterion("ARCHIVES_NO is not null");
            return (Criteria) this;
        }

        public Criteria andArchivesNoEqualTo(String value) {
            addCriterion("ARCHIVES_NO =", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoNotEqualTo(String value) {
            addCriterion("ARCHIVES_NO <>", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoGreaterThan(String value) {
            addCriterion("ARCHIVES_NO >", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoGreaterThanOrEqualTo(String value) {
            addCriterion("ARCHIVES_NO >=", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoLessThan(String value) {
            addCriterion("ARCHIVES_NO <", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoLessThanOrEqualTo(String value) {
            addCriterion("ARCHIVES_NO <=", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoLike(String value) {
            addCriterion("ARCHIVES_NO like", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoNotLike(String value) {
            addCriterion("ARCHIVES_NO not like", value, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoIn(List<String> values) {
            addCriterion("ARCHIVES_NO in", values, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoNotIn(List<String> values) {
            addCriterion("ARCHIVES_NO not in", values, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoBetween(String value1, String value2) {
            addCriterion("ARCHIVES_NO between", value1, value2, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andArchivesNoNotBetween(String value1, String value2) {
            addCriterion("ARCHIVES_NO not between", value1, value2, "archivesNo");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeIsNull() {
            addCriterion("BLOODPACKAGE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeIsNotNull() {
            addCriterion("BLOODPACKAGE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeEqualTo(String value) {
            addCriterion("BLOODPACKAGE_CODE =", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeNotEqualTo(String value) {
            addCriterion("BLOODPACKAGE_CODE <>", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeGreaterThan(String value) {
            addCriterion("BLOODPACKAGE_CODE >", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BLOODPACKAGE_CODE >=", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeLessThan(String value) {
            addCriterion("BLOODPACKAGE_CODE <", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeLessThanOrEqualTo(String value) {
            addCriterion("BLOODPACKAGE_CODE <=", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeLike(String value) {
            addCriterion("BLOODPACKAGE_CODE like", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeNotLike(String value) {
            addCriterion("BLOODPACKAGE_CODE not like", value, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeIn(List<String> values) {
            addCriterion("BLOODPACKAGE_CODE in", values, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeNotIn(List<String> values) {
            addCriterion("BLOODPACKAGE_CODE not in", values, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeBetween(String value1, String value2) {
            addCriterion("BLOODPACKAGE_CODE between", value1, value2, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andBloodpackageCodeNotBetween(String value1, String value2) {
            addCriterion("BLOODPACKAGE_CODE not between", value1, value2, "bloodpackageCode");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityIsNull() {
            addCriterion("PERIOD_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityIsNotNull() {
            addCriterion("PERIOD_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityEqualTo(Date value) {
            addCriterion("PERIOD_VALIDITY =", value, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityNotEqualTo(Date value) {
            addCriterion("PERIOD_VALIDITY <>", value, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityGreaterThan(Date value) {
            addCriterion("PERIOD_VALIDITY >", value, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityGreaterThanOrEqualTo(Date value) {
            addCriterion("PERIOD_VALIDITY >=", value, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityLessThan(Date value) {
            addCriterion("PERIOD_VALIDITY <", value, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityLessThanOrEqualTo(Date value) {
            addCriterion("PERIOD_VALIDITY <=", value, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityIn(List<Date> values) {
            addCriterion("PERIOD_VALIDITY in", values, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityNotIn(List<Date> values) {
            addCriterion("PERIOD_VALIDITY not in", values, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityBetween(Date value1, Date value2) {
            addCriterion("PERIOD_VALIDITY between", value1, value2, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPeriodValidityNotBetween(Date value1, Date value2) {
            addCriterion("PERIOD_VALIDITY not between", value1, value2, "periodValidity");
            return (Criteria) this;
        }

        public Criteria andPackageStateIsNull() {
            addCriterion("PACKAGE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andPackageStateIsNotNull() {
            addCriterion("PACKAGE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andPackageStateEqualTo(Short value) {
            addCriterion("PACKAGE_STATE =", value, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateNotEqualTo(Short value) {
            addCriterion("PACKAGE_STATE <>", value, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateGreaterThan(Short value) {
            addCriterion("PACKAGE_STATE >", value, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateGreaterThanOrEqualTo(Short value) {
            addCriterion("PACKAGE_STATE >=", value, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateLessThan(Short value) {
            addCriterion("PACKAGE_STATE <", value, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateLessThanOrEqualTo(Short value) {
            addCriterion("PACKAGE_STATE <=", value, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateIn(List<Short> values) {
            addCriterion("PACKAGE_STATE in", values, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateNotIn(List<Short> values) {
            addCriterion("PACKAGE_STATE not in", values, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateBetween(Short value1, Short value2) {
            addCriterion("PACKAGE_STATE between", value1, value2, "packageState");
            return (Criteria) this;
        }

        public Criteria andPackageStateNotBetween(Short value1, Short value2) {
            addCriterion("PACKAGE_STATE not between", value1, value2, "packageState");
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

        public Criteria andMaterlBatchIsNull() {
            addCriterion("MATERL_BATCH is null");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchIsNotNull() {
            addCriterion("MATERL_BATCH is not null");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchEqualTo(String value) {
            addCriterion("MATERL_BATCH =", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchNotEqualTo(String value) {
            addCriterion("MATERL_BATCH <>", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchGreaterThan(String value) {
            addCriterion("MATERL_BATCH >", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchGreaterThanOrEqualTo(String value) {
            addCriterion("MATERL_BATCH >=", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchLessThan(String value) {
            addCriterion("MATERL_BATCH <", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchLessThanOrEqualTo(String value) {
            addCriterion("MATERL_BATCH <=", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchLike(String value) {
            addCriterion("MATERL_BATCH like", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchNotLike(String value) {
            addCriterion("MATERL_BATCH not like", value, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchIn(List<String> values) {
            addCriterion("MATERL_BATCH in", values, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchNotIn(List<String> values) {
            addCriterion("MATERL_BATCH not in", values, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchBetween(String value1, String value2) {
            addCriterion("MATERL_BATCH between", value1, value2, "materlBatch");
            return (Criteria) this;
        }

        public Criteria andMaterlBatchNotBetween(String value1, String value2) {
            addCriterion("MATERL_BATCH not between", value1, value2, "materlBatch");
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