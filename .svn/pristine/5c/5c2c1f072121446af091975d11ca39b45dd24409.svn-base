package com.bdcor.pip.web.qn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PipCommEventCheck1Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipCommEventCheck1Example() {
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

        public Criteria andPidIsNull() {
            addCriterion("PID is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("PID is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("PID =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("PID <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("PID >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("PID >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("PID <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("PID <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("PID like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("PID not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("PID in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("PID not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("PID between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("PID not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andOriginDescIsNull() {
            addCriterion("ORIGIN_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOriginDescIsNotNull() {
            addCriterion("ORIGIN_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOriginDescEqualTo(String value) {
            addCriterion("ORIGIN_DESC =", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescNotEqualTo(String value) {
            addCriterion("ORIGIN_DESC <>", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescGreaterThan(String value) {
            addCriterion("ORIGIN_DESC >", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescGreaterThanOrEqualTo(String value) {
            addCriterion("ORIGIN_DESC >=", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescLessThan(String value) {
            addCriterion("ORIGIN_DESC <", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescLessThanOrEqualTo(String value) {
            addCriterion("ORIGIN_DESC <=", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescLike(String value) {
            addCriterion("ORIGIN_DESC like", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescNotLike(String value) {
            addCriterion("ORIGIN_DESC not like", value, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescIn(List<String> values) {
            addCriterion("ORIGIN_DESC in", values, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescNotIn(List<String> values) {
            addCriterion("ORIGIN_DESC not in", values, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescBetween(String value1, String value2) {
            addCriterion("ORIGIN_DESC between", value1, value2, "originDesc");
            return (Criteria) this;
        }

        public Criteria andOriginDescNotBetween(String value1, String value2) {
            addCriterion("ORIGIN_DESC not between", value1, value2, "originDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescIsNull() {
            addCriterion("CURRENT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCurrentDescIsNotNull() {
            addCriterion("CURRENT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentDescEqualTo(String value) {
            addCriterion("CURRENT_DESC =", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescNotEqualTo(String value) {
            addCriterion("CURRENT_DESC <>", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescGreaterThan(String value) {
            addCriterion("CURRENT_DESC >", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_DESC >=", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescLessThan(String value) {
            addCriterion("CURRENT_DESC <", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_DESC <=", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescLike(String value) {
            addCriterion("CURRENT_DESC like", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescNotLike(String value) {
            addCriterion("CURRENT_DESC not like", value, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescIn(List<String> values) {
            addCriterion("CURRENT_DESC in", values, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescNotIn(List<String> values) {
            addCriterion("CURRENT_DESC not in", values, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescBetween(String value1, String value2) {
            addCriterion("CURRENT_DESC between", value1, value2, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentDescNotBetween(String value1, String value2) {
            addCriterion("CURRENT_DESC not between", value1, value2, "currentDesc");
            return (Criteria) this;
        }

        public Criteria andIsDeadIsNull() {
            addCriterion("IS_DEAD is null");
            return (Criteria) this;
        }

        public Criteria andIsDeadIsNotNull() {
            addCriterion("IS_DEAD is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeadEqualTo(Short value) {
            addCriterion("IS_DEAD =", value, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadNotEqualTo(Short value) {
            addCriterion("IS_DEAD <>", value, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadGreaterThan(Short value) {
            addCriterion("IS_DEAD >", value, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_DEAD >=", value, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadLessThan(Short value) {
            addCriterion("IS_DEAD <", value, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadLessThanOrEqualTo(Short value) {
            addCriterion("IS_DEAD <=", value, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadIn(List<Short> values) {
            addCriterion("IS_DEAD in", values, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadNotIn(List<Short> values) {
            addCriterion("IS_DEAD not in", values, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadBetween(Short value1, Short value2) {
            addCriterion("IS_DEAD between", value1, value2, "isDead");
            return (Criteria) this;
        }

        public Criteria andIsDeadNotBetween(Short value1, Short value2) {
            addCriterion("IS_DEAD not between", value1, value2, "isDead");
            return (Criteria) this;
        }

        public Criteria andOccurDateIsNull() {
            addCriterion("OCCUR_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOccurDateIsNotNull() {
            addCriterion("OCCUR_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOccurDateEqualTo(Date value) {
            addCriterionForJDBCDate("OCCUR_DATE =", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("OCCUR_DATE <>", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateGreaterThan(Date value) {
            addCriterionForJDBCDate("OCCUR_DATE >", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OCCUR_DATE >=", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateLessThan(Date value) {
            addCriterionForJDBCDate("OCCUR_DATE <", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OCCUR_DATE <=", value, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateIn(List<Date> values) {
            addCriterionForJDBCDate("OCCUR_DATE in", values, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("OCCUR_DATE not in", values, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OCCUR_DATE between", value1, value2, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OCCUR_DATE not between", value1, value2, "occurDate");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeIsNull() {
            addCriterion("OCCUR_DATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeIsNotNull() {
            addCriterion("OCCUR_DATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeEqualTo(Short value) {
            addCriterion("OCCUR_DATE_TYPE =", value, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeNotEqualTo(Short value) {
            addCriterion("OCCUR_DATE_TYPE <>", value, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeGreaterThan(Short value) {
            addCriterion("OCCUR_DATE_TYPE >", value, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("OCCUR_DATE_TYPE >=", value, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeLessThan(Short value) {
            addCriterion("OCCUR_DATE_TYPE <", value, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeLessThanOrEqualTo(Short value) {
            addCriterion("OCCUR_DATE_TYPE <=", value, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeIn(List<Short> values) {
            addCriterion("OCCUR_DATE_TYPE in", values, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeNotIn(List<Short> values) {
            addCriterion("OCCUR_DATE_TYPE not in", values, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeBetween(Short value1, Short value2) {
            addCriterion("OCCUR_DATE_TYPE between", value1, value2, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andOccurDateTypeNotBetween(Short value1, Short value2) {
            addCriterion("OCCUR_DATE_TYPE not between", value1, value2, "occurDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateIsNull() {
            addCriterion("ENDING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndingDateIsNotNull() {
            addCriterion("ENDING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndingDateEqualTo(Date value) {
            addCriterionForJDBCDate("ENDING_DATE =", value, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ENDING_DATE <>", value, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ENDING_DATE >", value, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ENDING_DATE >=", value, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateLessThan(Date value) {
            addCriterionForJDBCDate("ENDING_DATE <", value, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ENDING_DATE <=", value, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateIn(List<Date> values) {
            addCriterionForJDBCDate("ENDING_DATE in", values, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ENDING_DATE not in", values, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ENDING_DATE between", value1, value2, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ENDING_DATE not between", value1, value2, "endingDate");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeIsNull() {
            addCriterion("ENDING_DATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeIsNotNull() {
            addCriterion("ENDING_DATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeEqualTo(Short value) {
            addCriterion("ENDING_DATE_TYPE =", value, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeNotEqualTo(Short value) {
            addCriterion("ENDING_DATE_TYPE <>", value, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeGreaterThan(Short value) {
            addCriterion("ENDING_DATE_TYPE >", value, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("ENDING_DATE_TYPE >=", value, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeLessThan(Short value) {
            addCriterion("ENDING_DATE_TYPE <", value, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeLessThanOrEqualTo(Short value) {
            addCriterion("ENDING_DATE_TYPE <=", value, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeIn(List<Short> values) {
            addCriterion("ENDING_DATE_TYPE in", values, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeNotIn(List<Short> values) {
            addCriterion("ENDING_DATE_TYPE not in", values, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeBetween(Short value1, Short value2) {
            addCriterion("ENDING_DATE_TYPE between", value1, value2, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andEndingDateTypeNotBetween(Short value1, Short value2) {
            addCriterion("ENDING_DATE_TYPE not between", value1, value2, "endingDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateIsNull() {
            addCriterion("DEAD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDeadDateIsNotNull() {
            addCriterion("DEAD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDeadDateEqualTo(Date value) {
            addCriterionForJDBCDate("DEAD_DATE =", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DEAD_DATE <>", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DEAD_DATE >", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DEAD_DATE >=", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateLessThan(Date value) {
            addCriterionForJDBCDate("DEAD_DATE <", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DEAD_DATE <=", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateIn(List<Date> values) {
            addCriterionForJDBCDate("DEAD_DATE in", values, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DEAD_DATE not in", values, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DEAD_DATE between", value1, value2, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DEAD_DATE not between", value1, value2, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeIsNull() {
            addCriterion("DEAD_DATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeIsNotNull() {
            addCriterion("DEAD_DATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeEqualTo(Short value) {
            addCriterion("DEAD_DATE_TYPE =", value, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeNotEqualTo(Short value) {
            addCriterion("DEAD_DATE_TYPE <>", value, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeGreaterThan(Short value) {
            addCriterion("DEAD_DATE_TYPE >", value, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("DEAD_DATE_TYPE >=", value, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeLessThan(Short value) {
            addCriterion("DEAD_DATE_TYPE <", value, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeLessThanOrEqualTo(Short value) {
            addCriterion("DEAD_DATE_TYPE <=", value, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeIn(List<Short> values) {
            addCriterion("DEAD_DATE_TYPE in", values, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeNotIn(List<Short> values) {
            addCriterion("DEAD_DATE_TYPE not in", values, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeBetween(Short value1, Short value2) {
            addCriterion("DEAD_DATE_TYPE between", value1, value2, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andDeadDateTypeNotBetween(Short value1, Short value2) {
            addCriterion("DEAD_DATE_TYPE not between", value1, value2, "deadDateType");
            return (Criteria) this;
        }

        public Criteria andEventPlaceIsNull() {
            addCriterion("EVENT_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andEventPlaceIsNotNull() {
            addCriterion("EVENT_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andEventPlaceEqualTo(Short value) {
            addCriterion("EVENT_PLACE =", value, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceNotEqualTo(Short value) {
            addCriterion("EVENT_PLACE <>", value, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceGreaterThan(Short value) {
            addCriterion("EVENT_PLACE >", value, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceGreaterThanOrEqualTo(Short value) {
            addCriterion("EVENT_PLACE >=", value, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceLessThan(Short value) {
            addCriterion("EVENT_PLACE <", value, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceLessThanOrEqualTo(Short value) {
            addCriterion("EVENT_PLACE <=", value, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceIn(List<Short> values) {
            addCriterion("EVENT_PLACE in", values, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceNotIn(List<Short> values) {
            addCriterion("EVENT_PLACE not in", values, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceBetween(Short value1, Short value2) {
            addCriterion("EVENT_PLACE between", value1, value2, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andEventPlaceNotBetween(Short value1, Short value2) {
            addCriterion("EVENT_PLACE not between", value1, value2, "eventPlace");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIsNull() {
            addCriterion("HOSPITAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIsNotNull() {
            addCriterion("HOSPITAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalNameEqualTo(String value) {
            addCriterion("HOSPITAL_NAME =", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_NAME <>", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameGreaterThan(String value) {
            addCriterion("HOSPITAL_NAME >", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_NAME >=", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLessThan(String value) {
            addCriterion("HOSPITAL_NAME <", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_NAME <=", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLike(String value) {
            addCriterion("HOSPITAL_NAME like", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotLike(String value) {
            addCriterion("HOSPITAL_NAME not like", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIn(List<String> values) {
            addCriterion("HOSPITAL_NAME in", values, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_NAME not in", values, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_NAME between", value1, value2, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_NAME not between", value1, value2, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayIsNull() {
            addCriterion("IN_HOSPITAL_DAY is null");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayIsNotNull() {
            addCriterion("IN_HOSPITAL_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayEqualTo(Short value) {
            addCriterion("IN_HOSPITAL_DAY =", value, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayNotEqualTo(Short value) {
            addCriterion("IN_HOSPITAL_DAY <>", value, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayGreaterThan(Short value) {
            addCriterion("IN_HOSPITAL_DAY >", value, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayGreaterThanOrEqualTo(Short value) {
            addCriterion("IN_HOSPITAL_DAY >=", value, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayLessThan(Short value) {
            addCriterion("IN_HOSPITAL_DAY <", value, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayLessThanOrEqualTo(Short value) {
            addCriterion("IN_HOSPITAL_DAY <=", value, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayIn(List<Short> values) {
            addCriterion("IN_HOSPITAL_DAY in", values, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayNotIn(List<Short> values) {
            addCriterion("IN_HOSPITAL_DAY not in", values, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayBetween(Short value1, Short value2) {
            addCriterion("IN_HOSPITAL_DAY between", value1, value2, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andInHospitalDayNotBetween(Short value1, Short value2) {
            addCriterion("IN_HOSPITAL_DAY not between", value1, value2, "inHospitalDay");
            return (Criteria) this;
        }

        public Criteria andReportRemarkIsNull() {
            addCriterion("REPORT_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andReportRemarkIsNotNull() {
            addCriterion("REPORT_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andReportRemarkEqualTo(String value) {
            addCriterion("REPORT_REMARK =", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkNotEqualTo(String value) {
            addCriterion("REPORT_REMARK <>", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkGreaterThan(String value) {
            addCriterion("REPORT_REMARK >", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_REMARK >=", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkLessThan(String value) {
            addCriterion("REPORT_REMARK <", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkLessThanOrEqualTo(String value) {
            addCriterion("REPORT_REMARK <=", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkLike(String value) {
            addCriterion("REPORT_REMARK like", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkNotLike(String value) {
            addCriterion("REPORT_REMARK not like", value, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkIn(List<String> values) {
            addCriterion("REPORT_REMARK in", values, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkNotIn(List<String> values) {
            addCriterion("REPORT_REMARK not in", values, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkBetween(String value1, String value2) {
            addCriterion("REPORT_REMARK between", value1, value2, "reportRemark");
            return (Criteria) this;
        }

        public Criteria andReportRemarkNotBetween(String value1, String value2) {
            addCriterion("REPORT_REMARK not between", value1, value2, "reportRemark");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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

        public Criteria andOriginEventEndingIsNull() {
            addCriterion("ORIGIN_EVENT_ENDING is null");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingIsNotNull() {
            addCriterion("ORIGIN_EVENT_ENDING is not null");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingEqualTo(String value) {
            addCriterion("ORIGIN_EVENT_ENDING =", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingNotEqualTo(String value) {
            addCriterion("ORIGIN_EVENT_ENDING <>", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingGreaterThan(String value) {
            addCriterion("ORIGIN_EVENT_ENDING >", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingGreaterThanOrEqualTo(String value) {
            addCriterion("ORIGIN_EVENT_ENDING >=", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingLessThan(String value) {
            addCriterion("ORIGIN_EVENT_ENDING <", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingLessThanOrEqualTo(String value) {
            addCriterion("ORIGIN_EVENT_ENDING <=", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingLike(String value) {
            addCriterion("ORIGIN_EVENT_ENDING like", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingNotLike(String value) {
            addCriterion("ORIGIN_EVENT_ENDING not like", value, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingIn(List<String> values) {
            addCriterion("ORIGIN_EVENT_ENDING in", values, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingNotIn(List<String> values) {
            addCriterion("ORIGIN_EVENT_ENDING not in", values, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingBetween(String value1, String value2) {
            addCriterion("ORIGIN_EVENT_ENDING between", value1, value2, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andOriginEventEndingNotBetween(String value1, String value2) {
            addCriterion("ORIGIN_EVENT_ENDING not between", value1, value2, "originEventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingIsNull() {
            addCriterion("EVENT_ENDING is null");
            return (Criteria) this;
        }

        public Criteria andEventEndingIsNotNull() {
            addCriterion("EVENT_ENDING is not null");
            return (Criteria) this;
        }

        public Criteria andEventEndingEqualTo(Short value) {
            addCriterion("EVENT_ENDING =", value, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingNotEqualTo(Short value) {
            addCriterion("EVENT_ENDING <>", value, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingGreaterThan(Short value) {
            addCriterion("EVENT_ENDING >", value, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingGreaterThanOrEqualTo(Short value) {
            addCriterion("EVENT_ENDING >=", value, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingLessThan(Short value) {
            addCriterion("EVENT_ENDING <", value, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingLessThanOrEqualTo(Short value) {
            addCriterion("EVENT_ENDING <=", value, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingIn(List<Short> values) {
            addCriterion("EVENT_ENDING in", values, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingNotIn(List<Short> values) {
            addCriterion("EVENT_ENDING not in", values, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingBetween(Short value1, Short value2) {
            addCriterion("EVENT_ENDING between", value1, value2, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andEventEndingNotBetween(Short value1, Short value2) {
            addCriterion("EVENT_ENDING not between", value1, value2, "eventEnding");
            return (Criteria) this;
        }

        public Criteria andReportStatusIsNull() {
            addCriterion("REPORT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andReportStatusIsNotNull() {
            addCriterion("REPORT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andReportStatusEqualTo(Short value) {
            addCriterion("REPORT_STATUS =", value, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusNotEqualTo(Short value) {
            addCriterion("REPORT_STATUS <>", value, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusGreaterThan(Short value) {
            addCriterion("REPORT_STATUS >", value, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("REPORT_STATUS >=", value, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusLessThan(Short value) {
            addCriterion("REPORT_STATUS <", value, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusLessThanOrEqualTo(Short value) {
            addCriterion("REPORT_STATUS <=", value, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusIn(List<Short> values) {
            addCriterion("REPORT_STATUS in", values, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusNotIn(List<Short> values) {
            addCriterion("REPORT_STATUS not in", values, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusBetween(Short value1, Short value2) {
            addCriterion("REPORT_STATUS between", value1, value2, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportStatusNotBetween(Short value1, Short value2) {
            addCriterion("REPORT_STATUS not between", value1, value2, "reportStatus");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("REPORT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("REPORT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(Date value) {
            addCriterion("REPORT_DATE =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(Date value) {
            addCriterion("REPORT_DATE <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(Date value) {
            addCriterion("REPORT_DATE >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(Date value) {
            addCriterion("REPORT_DATE >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(Date value) {
            addCriterion("REPORT_DATE <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(Date value) {
            addCriterion("REPORT_DATE <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<Date> values) {
            addCriterion("REPORT_DATE in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<Date> values) {
            addCriterion("REPORT_DATE not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(Date value1, Date value2) {
            addCriterion("REPORT_DATE between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(Date value1, Date value2) {
            addCriterion("REPORT_DATE not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportByIsNull() {
            addCriterion("REPORT_BY is null");
            return (Criteria) this;
        }

        public Criteria andReportByIsNotNull() {
            addCriterion("REPORT_BY is not null");
            return (Criteria) this;
        }

        public Criteria andReportByEqualTo(String value) {
            addCriterion("REPORT_BY =", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByNotEqualTo(String value) {
            addCriterion("REPORT_BY <>", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByGreaterThan(String value) {
            addCriterion("REPORT_BY >", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_BY >=", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByLessThan(String value) {
            addCriterion("REPORT_BY <", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByLessThanOrEqualTo(String value) {
            addCriterion("REPORT_BY <=", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByLike(String value) {
            addCriterion("REPORT_BY like", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByNotLike(String value) {
            addCriterion("REPORT_BY not like", value, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByIn(List<String> values) {
            addCriterion("REPORT_BY in", values, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByNotIn(List<String> values) {
            addCriterion("REPORT_BY not in", values, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByBetween(String value1, String value2) {
            addCriterion("REPORT_BY between", value1, value2, "reportBy");
            return (Criteria) this;
        }

        public Criteria andReportByNotBetween(String value1, String value2) {
            addCriterion("REPORT_BY not between", value1, value2, "reportBy");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkIsNull() {
            addCriterion("CURRENT_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkIsNotNull() {
            addCriterion("CURRENT_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkEqualTo(String value) {
            addCriterion("CURRENT_REMARK =", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkNotEqualTo(String value) {
            addCriterion("CURRENT_REMARK <>", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkGreaterThan(String value) {
            addCriterion("CURRENT_REMARK >", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_REMARK >=", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkLessThan(String value) {
            addCriterion("CURRENT_REMARK <", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_REMARK <=", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkLike(String value) {
            addCriterion("CURRENT_REMARK like", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkNotLike(String value) {
            addCriterion("CURRENT_REMARK not like", value, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkIn(List<String> values) {
            addCriterion("CURRENT_REMARK in", values, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkNotIn(List<String> values) {
            addCriterion("CURRENT_REMARK not in", values, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkBetween(String value1, String value2) {
            addCriterion("CURRENT_REMARK between", value1, value2, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andCurrentRemarkNotBetween(String value1, String value2) {
            addCriterion("CURRENT_REMARK not between", value1, value2, "currentRemark");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateIsNull() {
            addCriterion("ORIGIN_DEAD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateIsNotNull() {
            addCriterion("ORIGIN_DEAD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateEqualTo(Date value) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE =", value, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE <>", value, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE >", value, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE >=", value, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateLessThan(Date value) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE <", value, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE <=", value, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateIn(List<Date> values) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE in", values, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE not in", values, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE between", value1, value2, "originDeadDate");
            return (Criteria) this;
        }

        public Criteria andOriginDeadDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORIGIN_DEAD_DATE not between", value1, value2, "originDeadDate");
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