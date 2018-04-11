package com.bdcor.pip.web.material.supp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PipMmsExscmdetalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipMmsExscmdetalExample() {
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

        public Criteria andExorderNoIsNull() {
            addCriterion("EXORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andExorderNoIsNotNull() {
            addCriterion("EXORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andExorderNoEqualTo(String value) {
            addCriterion("EXORDER_NO =", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoNotEqualTo(String value) {
            addCriterion("EXORDER_NO <>", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoGreaterThan(String value) {
            addCriterion("EXORDER_NO >", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoGreaterThanOrEqualTo(String value) {
            addCriterion("EXORDER_NO >=", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoLessThan(String value) {
            addCriterion("EXORDER_NO <", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoLessThanOrEqualTo(String value) {
            addCriterion("EXORDER_NO <=", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoLike(String value) {
            addCriterion("EXORDER_NO like", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoNotLike(String value) {
            addCriterion("EXORDER_NO not like", value, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoIn(List<String> values) {
            addCriterion("EXORDER_NO in", values, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoNotIn(List<String> values) {
            addCriterion("EXORDER_NO not in", values, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoBetween(String value1, String value2) {
            addCriterion("EXORDER_NO between", value1, value2, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andExorderNoNotBetween(String value1, String value2) {
            addCriterion("EXORDER_NO not between", value1, value2, "exorderNo");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeIsNull() {
            addCriterion("MATERLINFO_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeIsNotNull() {
            addCriterion("MATERLINFO_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeEqualTo(String value) {
            addCriterion("MATERLINFO_CODE =", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeNotEqualTo(String value) {
            addCriterion("MATERLINFO_CODE <>", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeGreaterThan(String value) {
            addCriterion("MATERLINFO_CODE >", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MATERLINFO_CODE >=", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeLessThan(String value) {
            addCriterion("MATERLINFO_CODE <", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeLessThanOrEqualTo(String value) {
            addCriterion("MATERLINFO_CODE <=", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeLike(String value) {
            addCriterion("MATERLINFO_CODE like", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeNotLike(String value) {
            addCriterion("MATERLINFO_CODE not like", value, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeIn(List<String> values) {
            addCriterion("MATERLINFO_CODE in", values, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeNotIn(List<String> values) {
            addCriterion("MATERLINFO_CODE not in", values, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeBetween(String value1, String value2) {
            addCriterion("MATERLINFO_CODE between", value1, value2, "materlinfoCode");
            return (Criteria) this;
        }

        public Criteria andMaterlinfoCodeNotBetween(String value1, String value2) {
            addCriterion("MATERLINFO_CODE not between", value1, value2, "materlinfoCode");
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

        public Criteria andMaterlPriceIsNull() {
            addCriterion("MATERL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceIsNotNull() {
            addCriterion("MATERL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceEqualTo(BigDecimal value) {
            addCriterion("MATERL_PRICE =", value, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceNotEqualTo(BigDecimal value) {
            addCriterion("MATERL_PRICE <>", value, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceGreaterThan(BigDecimal value) {
            addCriterion("MATERL_PRICE >", value, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MATERL_PRICE >=", value, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceLessThan(BigDecimal value) {
            addCriterion("MATERL_PRICE <", value, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MATERL_PRICE <=", value, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceIn(List<BigDecimal> values) {
            addCriterion("MATERL_PRICE in", values, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceNotIn(List<BigDecimal> values) {
            addCriterion("MATERL_PRICE not in", values, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MATERL_PRICE between", value1, value2, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andMaterlPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MATERL_PRICE not between", value1, value2, "materlPrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceIsNull() {
            addCriterion("WHOLESALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceIsNotNull() {
            addCriterion("WHOLESALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceEqualTo(BigDecimal value) {
            addCriterion("WHOLESALE_PRICE =", value, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceNotEqualTo(BigDecimal value) {
            addCriterion("WHOLESALE_PRICE <>", value, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceGreaterThan(BigDecimal value) {
            addCriterion("WHOLESALE_PRICE >", value, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WHOLESALE_PRICE >=", value, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceLessThan(BigDecimal value) {
            addCriterion("WHOLESALE_PRICE <", value, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WHOLESALE_PRICE <=", value, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceIn(List<BigDecimal> values) {
            addCriterion("WHOLESALE_PRICE in", values, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceNotIn(List<BigDecimal> values) {
            addCriterion("WHOLESALE_PRICE not in", values, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WHOLESALE_PRICE between", value1, value2, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andWholesalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WHOLESALE_PRICE not between", value1, value2, "wholesalePrice");
            return (Criteria) this;
        }

        public Criteria andStoreUnitIsNull() {
            addCriterion("STORE_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andStoreUnitIsNotNull() {
            addCriterion("STORE_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andStoreUnitEqualTo(String value) {
            addCriterion("STORE_UNIT =", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotEqualTo(String value) {
            addCriterion("STORE_UNIT <>", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitGreaterThan(String value) {
            addCriterion("STORE_UNIT >", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitGreaterThanOrEqualTo(String value) {
            addCriterion("STORE_UNIT >=", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitLessThan(String value) {
            addCriterion("STORE_UNIT <", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitLessThanOrEqualTo(String value) {
            addCriterion("STORE_UNIT <=", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitLike(String value) {
            addCriterion("STORE_UNIT like", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotLike(String value) {
            addCriterion("STORE_UNIT not like", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitIn(List<String> values) {
            addCriterion("STORE_UNIT in", values, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotIn(List<String> values) {
            addCriterion("STORE_UNIT not in", values, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitBetween(String value1, String value2) {
            addCriterion("STORE_UNIT between", value1, value2, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotBetween(String value1, String value2) {
            addCriterion("STORE_UNIT not between", value1, value2, "storeUnit");
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

        public Criteria andManufacturerCodeIsNull() {
            addCriterion("MANUFACTURER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeIsNotNull() {
            addCriterion("MANUFACTURER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeEqualTo(String value) {
            addCriterion("MANUFACTURER_CODE =", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeNotEqualTo(String value) {
            addCriterion("MANUFACTURER_CODE <>", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeGreaterThan(String value) {
            addCriterion("MANUFACTURER_CODE >", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MANUFACTURER_CODE >=", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeLessThan(String value) {
            addCriterion("MANUFACTURER_CODE <", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeLessThanOrEqualTo(String value) {
            addCriterion("MANUFACTURER_CODE <=", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeLike(String value) {
            addCriterion("MANUFACTURER_CODE like", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeNotLike(String value) {
            addCriterion("MANUFACTURER_CODE not like", value, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeIn(List<String> values) {
            addCriterion("MANUFACTURER_CODE in", values, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeNotIn(List<String> values) {
            addCriterion("MANUFACTURER_CODE not in", values, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeBetween(String value1, String value2) {
            addCriterion("MANUFACTURER_CODE between", value1, value2, "manufacturerCode");
            return (Criteria) this;
        }

        public Criteria andManufacturerCodeNotBetween(String value1, String value2) {
            addCriterion("MANUFACTURER_CODE not between", value1, value2, "manufacturerCode");
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