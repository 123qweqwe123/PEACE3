package com.bdcor.pip.web.qn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PipCommEventCheckErExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipCommEventCheckErExample() {
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

        public Criteria andCurrentdescIsNull() {
            addCriterion("CURRENTDESC is null");
            return (Criteria) this;
        }

        public Criteria andCurrentdescIsNotNull() {
            addCriterion("CURRENTDESC is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentdescEqualTo(String value) {
            addCriterion("CURRENTDESC =", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescNotEqualTo(String value) {
            addCriterion("CURRENTDESC <>", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescGreaterThan(String value) {
            addCriterion("CURRENTDESC >", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENTDESC >=", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescLessThan(String value) {
            addCriterion("CURRENTDESC <", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescLessThanOrEqualTo(String value) {
            addCriterion("CURRENTDESC <=", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescLike(String value) {
            addCriterion("CURRENTDESC like", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescNotLike(String value) {
            addCriterion("CURRENTDESC not like", value, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescIn(List<String> values) {
            addCriterion("CURRENTDESC in", values, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescNotIn(List<String> values) {
            addCriterion("CURRENTDESC not in", values, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescBetween(String value1, String value2) {
            addCriterion("CURRENTDESC between", value1, value2, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andCurrentdescNotBetween(String value1, String value2) {
            addCriterion("CURRENTDESC not between", value1, value2, "currentdesc");
            return (Criteria) this;
        }

        public Criteria andXjbzwIsNull() {
            addCriterion("XJBZW is null");
            return (Criteria) this;
        }

        public Criteria andXjbzwIsNotNull() {
            addCriterion("XJBZW is not null");
            return (Criteria) this;
        }

        public Criteria andXjbzwEqualTo(Short value) {
            addCriterion("XJBZW =", value, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwNotEqualTo(Short value) {
            addCriterion("XJBZW <>", value, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwGreaterThan(Short value) {
            addCriterion("XJBZW >", value, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwGreaterThanOrEqualTo(Short value) {
            addCriterion("XJBZW >=", value, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwLessThan(Short value) {
            addCriterion("XJBZW <", value, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwLessThanOrEqualTo(Short value) {
            addCriterion("XJBZW <=", value, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwIn(List<Short> values) {
            addCriterion("XJBZW in", values, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwNotIn(List<Short> values) {
            addCriterion("XJBZW not in", values, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwBetween(Short value1, Short value2) {
            addCriterion("XJBZW between", value1, value2, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwNotBetween(Short value1, Short value2) {
            addCriterion("XJBZW not between", value1, value2, "xjbzw");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeIsNull() {
            addCriterion("XJBZWTYPE is null");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeIsNotNull() {
            addCriterion("XJBZWTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeEqualTo(Short value) {
            addCriterion("XJBZWTYPE =", value, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeNotEqualTo(Short value) {
            addCriterion("XJBZWTYPE <>", value, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeGreaterThan(Short value) {
            addCriterion("XJBZWTYPE >", value, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("XJBZWTYPE >=", value, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeLessThan(Short value) {
            addCriterion("XJBZWTYPE <", value, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeLessThanOrEqualTo(Short value) {
            addCriterion("XJBZWTYPE <=", value, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeIn(List<Short> values) {
            addCriterion("XJBZWTYPE in", values, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeNotIn(List<Short> values) {
            addCriterion("XJBZWTYPE not in", values, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeBetween(Short value1, Short value2) {
            addCriterion("XJBZWTYPE between", value1, value2, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andXjbzwtypeNotBetween(Short value1, Short value2) {
            addCriterion("XJBZWTYPE not between", value1, value2, "xjbzwtype");
            return (Criteria) this;
        }

        public Criteria andFzresultIsNull() {
            addCriterion("FZRESULT is null");
            return (Criteria) this;
        }

        public Criteria andFzresultIsNotNull() {
            addCriterion("FZRESULT is not null");
            return (Criteria) this;
        }

        public Criteria andFzresultEqualTo(String value) {
            addCriterion("FZRESULT =", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultNotEqualTo(String value) {
            addCriterion("FZRESULT <>", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultGreaterThan(String value) {
            addCriterion("FZRESULT >", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultGreaterThanOrEqualTo(String value) {
            addCriterion("FZRESULT >=", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultLessThan(String value) {
            addCriterion("FZRESULT <", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultLessThanOrEqualTo(String value) {
            addCriterion("FZRESULT <=", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultLike(String value) {
            addCriterion("FZRESULT like", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultNotLike(String value) {
            addCriterion("FZRESULT not like", value, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultIn(List<String> values) {
            addCriterion("FZRESULT in", values, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultNotIn(List<String> values) {
            addCriterion("FZRESULT not in", values, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultBetween(String value1, String value2) {
            addCriterion("FZRESULT between", value1, value2, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzresultNotBetween(String value1, String value2) {
            addCriterion("FZRESULT not between", value1, value2, "fzresult");
            return (Criteria) this;
        }

        public Criteria andFzunitIsNull() {
            addCriterion("FZUNIT is null");
            return (Criteria) this;
        }

        public Criteria andFzunitIsNotNull() {
            addCriterion("FZUNIT is not null");
            return (Criteria) this;
        }

        public Criteria andFzunitEqualTo(Short value) {
            addCriterion("FZUNIT =", value, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitNotEqualTo(Short value) {
            addCriterion("FZUNIT <>", value, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitGreaterThan(Short value) {
            addCriterion("FZUNIT >", value, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitGreaterThanOrEqualTo(Short value) {
            addCriterion("FZUNIT >=", value, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitLessThan(Short value) {
            addCriterion("FZUNIT <", value, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitLessThanOrEqualTo(Short value) {
            addCriterion("FZUNIT <=", value, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitIn(List<Short> values) {
            addCriterion("FZUNIT in", values, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitNotIn(List<Short> values) {
            addCriterion("FZUNIT not in", values, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitBetween(Short value1, Short value2) {
            addCriterion("FZUNIT between", value1, value2, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzunitNotBetween(Short value1, Short value2) {
            addCriterion("FZUNIT not between", value1, value2, "fzunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitIsNull() {
            addCriterion("FZUPLIMIT is null");
            return (Criteria) this;
        }

        public Criteria andFzuplimitIsNotNull() {
            addCriterion("FZUPLIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andFzuplimitEqualTo(String value) {
            addCriterion("FZUPLIMIT =", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitNotEqualTo(String value) {
            addCriterion("FZUPLIMIT <>", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitGreaterThan(String value) {
            addCriterion("FZUPLIMIT >", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitGreaterThanOrEqualTo(String value) {
            addCriterion("FZUPLIMIT >=", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitLessThan(String value) {
            addCriterion("FZUPLIMIT <", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitLessThanOrEqualTo(String value) {
            addCriterion("FZUPLIMIT <=", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitLike(String value) {
            addCriterion("FZUPLIMIT like", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitNotLike(String value) {
            addCriterion("FZUPLIMIT not like", value, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitIn(List<String> values) {
            addCriterion("FZUPLIMIT in", values, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitNotIn(List<String> values) {
            addCriterion("FZUPLIMIT not in", values, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitBetween(String value1, String value2) {
            addCriterion("FZUPLIMIT between", value1, value2, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitNotBetween(String value1, String value2) {
            addCriterion("FZUPLIMIT not between", value1, value2, "fzuplimit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitIsNull() {
            addCriterion("FZUPLIMITUNIT is null");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitIsNotNull() {
            addCriterion("FZUPLIMITUNIT is not null");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitEqualTo(Short value) {
            addCriterion("FZUPLIMITUNIT =", value, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitNotEqualTo(Short value) {
            addCriterion("FZUPLIMITUNIT <>", value, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitGreaterThan(Short value) {
            addCriterion("FZUPLIMITUNIT >", value, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitGreaterThanOrEqualTo(Short value) {
            addCriterion("FZUPLIMITUNIT >=", value, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitLessThan(Short value) {
            addCriterion("FZUPLIMITUNIT <", value, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitLessThanOrEqualTo(Short value) {
            addCriterion("FZUPLIMITUNIT <=", value, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitIn(List<Short> values) {
            addCriterion("FZUPLIMITUNIT in", values, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitNotIn(List<Short> values) {
            addCriterion("FZUPLIMITUNIT not in", values, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitBetween(Short value1, Short value2) {
            addCriterion("FZUPLIMITUNIT between", value1, value2, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andFzuplimitunitNotBetween(Short value1, Short value2) {
            addCriterion("FZUPLIMITUNIT not between", value1, value2, "fzuplimitunit");
            return (Criteria) this;
        }

        public Criteria andIsxdtIsNull() {
            addCriterion("ISXDT is null");
            return (Criteria) this;
        }

        public Criteria andIsxdtIsNotNull() {
            addCriterion("ISXDT is not null");
            return (Criteria) this;
        }

        public Criteria andIsxdtEqualTo(Short value) {
            addCriterion("ISXDT =", value, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtNotEqualTo(Short value) {
            addCriterion("ISXDT <>", value, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtGreaterThan(Short value) {
            addCriterion("ISXDT >", value, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtGreaterThanOrEqualTo(Short value) {
            addCriterion("ISXDT >=", value, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtLessThan(Short value) {
            addCriterion("ISXDT <", value, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtLessThanOrEqualTo(Short value) {
            addCriterion("ISXDT <=", value, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtIn(List<Short> values) {
            addCriterion("ISXDT in", values, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtNotIn(List<Short> values) {
            addCriterion("ISXDT not in", values, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtBetween(Short value1, Short value2) {
            addCriterion("ISXDT between", value1, value2, "isxdt");
            return (Criteria) this;
        }

        public Criteria andIsxdtNotBetween(Short value1, Short value2) {
            addCriterion("ISXDT not between", value1, value2, "isxdt");
            return (Criteria) this;
        }

        public Criteria andXdtverityIsNull() {
            addCriterion("XDTVERITY is null");
            return (Criteria) this;
        }

        public Criteria andXdtverityIsNotNull() {
            addCriterion("XDTVERITY is not null");
            return (Criteria) this;
        }

        public Criteria andXdtverityEqualTo(Short value) {
            addCriterion("XDTVERITY =", value, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityNotEqualTo(Short value) {
            addCriterion("XDTVERITY <>", value, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityGreaterThan(Short value) {
            addCriterion("XDTVERITY >", value, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityGreaterThanOrEqualTo(Short value) {
            addCriterion("XDTVERITY >=", value, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityLessThan(Short value) {
            addCriterion("XDTVERITY <", value, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityLessThanOrEqualTo(Short value) {
            addCriterion("XDTVERITY <=", value, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityIn(List<Short> values) {
            addCriterion("XDTVERITY in", values, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityNotIn(List<Short> values) {
            addCriterion("XDTVERITY not in", values, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityBetween(Short value1, Short value2) {
            addCriterion("XDTVERITY between", value1, value2, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andXdtverityNotBetween(Short value1, Short value2) {
            addCriterion("XDTVERITY not between", value1, value2, "xdtverity");
            return (Criteria) this;
        }

        public Criteria andKilliprankIsNull() {
            addCriterion("KILLIPRANK is null");
            return (Criteria) this;
        }

        public Criteria andKilliprankIsNotNull() {
            addCriterion("KILLIPRANK is not null");
            return (Criteria) this;
        }

        public Criteria andKilliprankEqualTo(Short value) {
            addCriterion("KILLIPRANK =", value, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankNotEqualTo(Short value) {
            addCriterion("KILLIPRANK <>", value, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankGreaterThan(Short value) {
            addCriterion("KILLIPRANK >", value, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankGreaterThanOrEqualTo(Short value) {
            addCriterion("KILLIPRANK >=", value, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankLessThan(Short value) {
            addCriterion("KILLIPRANK <", value, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankLessThanOrEqualTo(Short value) {
            addCriterion("KILLIPRANK <=", value, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankIn(List<Short> values) {
            addCriterion("KILLIPRANK in", values, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankNotIn(List<Short> values) {
            addCriterion("KILLIPRANK not in", values, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankBetween(Short value1, Short value2) {
            addCriterion("KILLIPRANK between", value1, value2, "killiprank");
            return (Criteria) this;
        }

        public Criteria andKilliprankNotBetween(Short value1, Short value2) {
            addCriterion("KILLIPRANK not between", value1, value2, "killiprank");
            return (Criteria) this;
        }

        public Criteria andGzxgcjIsNull() {
            addCriterion("GZXGCJ is null");
            return (Criteria) this;
        }

        public Criteria andGzxgcjIsNotNull() {
            addCriterion("GZXGCJ is not null");
            return (Criteria) this;
        }

        public Criteria andGzxgcjEqualTo(Short value) {
            addCriterion("GZXGCJ =", value, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjNotEqualTo(Short value) {
            addCriterion("GZXGCJ <>", value, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjGreaterThan(Short value) {
            addCriterion("GZXGCJ >", value, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjGreaterThanOrEqualTo(Short value) {
            addCriterion("GZXGCJ >=", value, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjLessThan(Short value) {
            addCriterion("GZXGCJ <", value, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjLessThanOrEqualTo(Short value) {
            addCriterion("GZXGCJ <=", value, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjIn(List<Short> values) {
            addCriterion("GZXGCJ in", values, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjNotIn(List<Short> values) {
            addCriterion("GZXGCJ not in", values, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjBetween(Short value1, Short value2) {
            addCriterion("GZXGCJ between", value1, value2, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andGzxgcjNotBetween(Short value1, Short value2) {
            addCriterion("GZXGCJ not between", value1, value2, "gzxgcj");
            return (Criteria) this;
        }

        public Criteria andZccdIsNull() {
            addCriterion("ZCCD is null");
            return (Criteria) this;
        }

        public Criteria andZccdIsNotNull() {
            addCriterion("ZCCD is not null");
            return (Criteria) this;
        }

        public Criteria andZccdEqualTo(Short value) {
            addCriterion("ZCCD =", value, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdNotEqualTo(Short value) {
            addCriterion("ZCCD <>", value, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdGreaterThan(Short value) {
            addCriterion("ZCCD >", value, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdGreaterThanOrEqualTo(Short value) {
            addCriterion("ZCCD >=", value, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdLessThan(Short value) {
            addCriterion("ZCCD <", value, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdLessThanOrEqualTo(Short value) {
            addCriterion("ZCCD <=", value, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdIn(List<Short> values) {
            addCriterion("ZCCD in", values, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdNotIn(List<Short> values) {
            addCriterion("ZCCD not in", values, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdBetween(Short value1, Short value2) {
            addCriterion("ZCCD between", value1, value2, "zccd");
            return (Criteria) this;
        }

        public Criteria andZccdNotBetween(Short value1, Short value2) {
            addCriterion("ZCCD not between", value1, value2, "zccd");
            return (Criteria) this;
        }

        public Criteria andGsbwIsNull() {
            addCriterion("GSBW is null");
            return (Criteria) this;
        }

        public Criteria andGsbwIsNotNull() {
            addCriterion("GSBW is not null");
            return (Criteria) this;
        }

        public Criteria andGsbwEqualTo(Short value) {
            addCriterion("GSBW =", value, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwNotEqualTo(Short value) {
            addCriterion("GSBW <>", value, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwGreaterThan(Short value) {
            addCriterion("GSBW >", value, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwGreaterThanOrEqualTo(Short value) {
            addCriterion("GSBW >=", value, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwLessThan(Short value) {
            addCriterion("GSBW <", value, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwLessThanOrEqualTo(Short value) {
            addCriterion("GSBW <=", value, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwIn(List<Short> values) {
            addCriterion("GSBW in", values, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwNotIn(List<Short> values) {
            addCriterion("GSBW not in", values, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwBetween(Short value1, Short value2) {
            addCriterion("GSBW between", value1, value2, "gsbw");
            return (Criteria) this;
        }

        public Criteria andGsbwNotBetween(Short value1, Short value2) {
            addCriterion("GSBW not between", value1, value2, "gsbw");
            return (Criteria) this;
        }

        public Criteria andCxtypeIsNull() {
            addCriterion("CXTYPE is null");
            return (Criteria) this;
        }

        public Criteria andCxtypeIsNotNull() {
            addCriterion("CXTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCxtypeEqualTo(Short value) {
            addCriterion("CXTYPE =", value, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeNotEqualTo(Short value) {
            addCriterion("CXTYPE <>", value, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeGreaterThan(Short value) {
            addCriterion("CXTYPE >", value, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("CXTYPE >=", value, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeLessThan(Short value) {
            addCriterion("CXTYPE <", value, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeLessThanOrEqualTo(Short value) {
            addCriterion("CXTYPE <=", value, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeIn(List<Short> values) {
            addCriterion("CXTYPE in", values, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeNotIn(List<Short> values) {
            addCriterion("CXTYPE not in", values, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeBetween(Short value1, Short value2) {
            addCriterion("CXTYPE between", value1, value2, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxtypeNotBetween(Short value1, Short value2) {
            addCriterion("CXTYPE not between", value1, value2, "cxtype");
            return (Criteria) this;
        }

        public Criteria andCxplaceIsNull() {
            addCriterion("CXPLACE is null");
            return (Criteria) this;
        }

        public Criteria andCxplaceIsNotNull() {
            addCriterion("CXPLACE is not null");
            return (Criteria) this;
        }

        public Criteria andCxplaceEqualTo(Short value) {
            addCriterion("CXPLACE =", value, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceNotEqualTo(Short value) {
            addCriterion("CXPLACE <>", value, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceGreaterThan(Short value) {
            addCriterion("CXPLACE >", value, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceGreaterThanOrEqualTo(Short value) {
            addCriterion("CXPLACE >=", value, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceLessThan(Short value) {
            addCriterion("CXPLACE <", value, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceLessThanOrEqualTo(Short value) {
            addCriterion("CXPLACE <=", value, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceIn(List<Short> values) {
            addCriterion("CXPLACE in", values, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceNotIn(List<Short> values) {
            addCriterion("CXPLACE not in", values, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceBetween(Short value1, Short value2) {
            addCriterion("CXPLACE between", value1, value2, "cxplace");
            return (Criteria) this;
        }

        public Criteria andCxplaceNotBetween(Short value1, Short value2) {
            addCriterion("CXPLACE not between", value1, value2, "cxplace");
            return (Criteria) this;
        }

        public Criteria andNyhatypeIsNull() {
            addCriterion("NYHATYPE is null");
            return (Criteria) this;
        }

        public Criteria andNyhatypeIsNotNull() {
            addCriterion("NYHATYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNyhatypeEqualTo(Short value) {
            addCriterion("NYHATYPE =", value, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeNotEqualTo(Short value) {
            addCriterion("NYHATYPE <>", value, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeGreaterThan(Short value) {
            addCriterion("NYHATYPE >", value, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeGreaterThanOrEqualTo(Short value) {
            addCriterion("NYHATYPE >=", value, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeLessThan(Short value) {
            addCriterion("NYHATYPE <", value, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeLessThanOrEqualTo(Short value) {
            addCriterion("NYHATYPE <=", value, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeIn(List<Short> values) {
            addCriterion("NYHATYPE in", values, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeNotIn(List<Short> values) {
            addCriterion("NYHATYPE not in", values, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeBetween(Short value1, Short value2) {
            addCriterion("NYHATYPE between", value1, value2, "nyhatype");
            return (Criteria) this;
        }

        public Criteria andNyhatypeNotBetween(Short value1, Short value2) {
            addCriterion("NYHATYPE not between", value1, value2, "nyhatype");
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
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
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
            addCriterionForJDBCDate("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_TIME not between", value1, value2, "updateTime");
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