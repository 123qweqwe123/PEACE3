package com.bdcor.pip.web.msg.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PipMsgHandlerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipMsgHandlerExample() {
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

        public Criteria andMsgsendIdIsNull() {
            addCriterion("MSGSEND_ID is null");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdIsNotNull() {
            addCriterion("MSGSEND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdEqualTo(String value) {
            addCriterion("MSGSEND_ID =", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdNotEqualTo(String value) {
            addCriterion("MSGSEND_ID <>", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdGreaterThan(String value) {
            addCriterion("MSGSEND_ID >", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdGreaterThanOrEqualTo(String value) {
            addCriterion("MSGSEND_ID >=", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdLessThan(String value) {
            addCriterion("MSGSEND_ID <", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdLessThanOrEqualTo(String value) {
            addCriterion("MSGSEND_ID <=", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdLike(String value) {
            addCriterion("MSGSEND_ID like", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdNotLike(String value) {
            addCriterion("MSGSEND_ID not like", value, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdIn(List<String> values) {
            addCriterion("MSGSEND_ID in", values, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdNotIn(List<String> values) {
            addCriterion("MSGSEND_ID not in", values, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdBetween(String value1, String value2) {
            addCriterion("MSGSEND_ID between", value1, value2, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgsendIdNotBetween(String value1, String value2) {
            addCriterion("MSGSEND_ID not between", value1, value2, "msgsendId");
            return (Criteria) this;
        }

        public Criteria andMsgNameIsNull() {
            addCriterion("MSG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMsgNameIsNotNull() {
            addCriterion("MSG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMsgNameEqualTo(String value) {
            addCriterion("MSG_NAME =", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameNotEqualTo(String value) {
            addCriterion("MSG_NAME <>", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameGreaterThan(String value) {
            addCriterion("MSG_NAME >", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_NAME >=", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameLessThan(String value) {
            addCriterion("MSG_NAME <", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameLessThanOrEqualTo(String value) {
            addCriterion("MSG_NAME <=", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameLike(String value) {
            addCriterion("MSG_NAME like", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameNotLike(String value) {
            addCriterion("MSG_NAME not like", value, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameIn(List<String> values) {
            addCriterion("MSG_NAME in", values, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameNotIn(List<String> values) {
            addCriterion("MSG_NAME not in", values, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameBetween(String value1, String value2) {
            addCriterion("MSG_NAME between", value1, value2, "msgName");
            return (Criteria) this;
        }

        public Criteria andMsgNameNotBetween(String value1, String value2) {
            addCriterion("MSG_NAME not between", value1, value2, "msgName");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("MOBILE =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("MOBILE <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("MOBILE >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("MOBILE <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("MOBILE <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("MOBILE like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("MOBILE not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("MOBILE in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("MOBILE not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("MOBILE between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("MOBILE not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andIsHandlerIsNull() {
            addCriterion("IS_HANDLER is null");
            return (Criteria) this;
        }

        public Criteria andIsHandlerIsNotNull() {
            addCriterion("IS_HANDLER is not null");
            return (Criteria) this;
        }

        public Criteria andIsHandlerEqualTo(Integer value) {
            addCriterion("IS_HANDLER =", value, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerNotEqualTo(Integer value) {
            addCriterion("IS_HANDLER <>", value, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerGreaterThan(Integer value) {
            addCriterion("IS_HANDLER >", value, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_HANDLER >=", value, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerLessThan(Integer value) {
            addCriterion("IS_HANDLER <", value, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerLessThanOrEqualTo(Integer value) {
            addCriterion("IS_HANDLER <=", value, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerIn(List<Integer> values) {
            addCriterion("IS_HANDLER in", values, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerNotIn(List<Integer> values) {
            addCriterion("IS_HANDLER not in", values, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerBetween(Integer value1, Integer value2) {
            addCriterion("IS_HANDLER between", value1, value2, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsHandlerNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_HANDLER not between", value1, value2, "isHandler");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNull() {
            addCriterion("IS_PASS is null");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNotNull() {
            addCriterion("IS_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassEqualTo(Integer value) {
            addCriterion("IS_PASS =", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotEqualTo(Integer value) {
            addCriterion("IS_PASS <>", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThan(Integer value) {
            addCriterion("IS_PASS >", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_PASS >=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThan(Integer value) {
            addCriterion("IS_PASS <", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThanOrEqualTo(Integer value) {
            addCriterion("IS_PASS <=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassIn(List<Integer> values) {
            addCriterion("IS_PASS in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotIn(List<Integer> values) {
            addCriterion("IS_PASS not in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassBetween(Integer value1, Integer value2) {
            addCriterion("IS_PASS between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_PASS not between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andHandlerDateIsNull() {
            addCriterion("HANDLER_DATE is null");
            return (Criteria) this;
        }

        public Criteria andHandlerDateIsNotNull() {
            addCriterion("HANDLER_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerDateEqualTo(Date value) {
            addCriterion("HANDLER_DATE =", value, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateNotEqualTo(Date value) {
            addCriterion("HANDLER_DATE <>", value, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateGreaterThan(Date value) {
            addCriterion("HANDLER_DATE >", value, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateGreaterThanOrEqualTo(Date value) {
            addCriterion("HANDLER_DATE >=", value, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateLessThan(Date value) {
            addCriterion("HANDLER_DATE <", value, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateLessThanOrEqualTo(Date value) {
            addCriterion("HANDLER_DATE <=", value, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateIn(List<Date> values) {
            addCriterion("HANDLER_DATE in", values, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateNotIn(List<Date> values) {
            addCriterion("HANDLER_DATE not in", values, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateBetween(Date value1, Date value2) {
            addCriterion("HANDLER_DATE between", value1, value2, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andHandlerDateNotBetween(Date value1, Date value2) {
            addCriterion("HANDLER_DATE not between", value1, value2, "handlerDate");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgIsNull() {
            addCriterion("IS_GETMSG is null");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgIsNotNull() {
            addCriterion("IS_GETMSG is not null");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgEqualTo(Integer value) {
            addCriterion("IS_GETMSG =", value, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgNotEqualTo(Integer value) {
            addCriterion("IS_GETMSG <>", value, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgGreaterThan(Integer value) {
            addCriterion("IS_GETMSG >", value, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_GETMSG >=", value, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgLessThan(Integer value) {
            addCriterion("IS_GETMSG <", value, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgLessThanOrEqualTo(Integer value) {
            addCriterion("IS_GETMSG <=", value, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgIn(List<Integer> values) {
            addCriterion("IS_GETMSG in", values, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgNotIn(List<Integer> values) {
            addCriterion("IS_GETMSG not in", values, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgBetween(Integer value1, Integer value2) {
            addCriterion("IS_GETMSG between", value1, value2, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andIsGetmsgNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_GETMSG not between", value1, value2, "isGetmsg");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonIsNull() {
            addCriterion("NOREPLY_REASON is null");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonIsNotNull() {
            addCriterion("NOREPLY_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonEqualTo(String value) {
            addCriterion("NOREPLY_REASON =", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonNotEqualTo(String value) {
            addCriterion("NOREPLY_REASON <>", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonGreaterThan(String value) {
            addCriterion("NOREPLY_REASON >", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonGreaterThanOrEqualTo(String value) {
            addCriterion("NOREPLY_REASON >=", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonLessThan(String value) {
            addCriterion("NOREPLY_REASON <", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonLessThanOrEqualTo(String value) {
            addCriterion("NOREPLY_REASON <=", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonLike(String value) {
            addCriterion("NOREPLY_REASON like", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonNotLike(String value) {
            addCriterion("NOREPLY_REASON not like", value, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonIn(List<String> values) {
            addCriterion("NOREPLY_REASON in", values, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonNotIn(List<String> values) {
            addCriterion("NOREPLY_REASON not in", values, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonBetween(String value1, String value2) {
            addCriterion("NOREPLY_REASON between", value1, value2, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andNoreplyReasonNotBetween(String value1, String value2) {
            addCriterion("NOREPLY_REASON not between", value1, value2, "noreplyReason");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNull() {
            addCriterion("CREATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNotNull() {
            addCriterion("CREATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateNameEqualTo(String value) {
            addCriterion("CREATE_NAME =", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotEqualTo(String value) {
            addCriterion("CREATE_NAME <>", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThan(String value) {
            addCriterion("CREATE_NAME >", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME >=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThan(String value) {
            addCriterion("CREATE_NAME <", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME <=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLike(String value) {
            addCriterion("CREATE_NAME like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotLike(String value) {
            addCriterion("CREATE_NAME not like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameIn(List<String> values) {
            addCriterion("CREATE_NAME in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotIn(List<String> values) {
            addCriterion("CREATE_NAME not in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameBetween(String value1, String value2) {
            addCriterion("CREATE_NAME between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_NAME not between", value1, value2, "createName");
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

        public Criteria andBzIsNull() {
            addCriterion("BZ is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("BZ is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("BZ =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("BZ <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("BZ >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("BZ >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("BZ <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("BZ <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("BZ like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("BZ not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("BZ in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("BZ not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("BZ between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("BZ not between", value1, value2, "bz");
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