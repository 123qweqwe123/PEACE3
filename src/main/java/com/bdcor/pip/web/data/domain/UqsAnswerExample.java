package com.bdcor.pip.web.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UqsAnswerExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public UqsAnswerExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ANSWER
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PIP_UQS_ANSWER
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
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
			addCriterion("QUESTIONNAIRE_ID between", value1, value2,
					"questionnaireId");
			return (Criteria) this;
		}

		public Criteria andQuestionnaireIdNotBetween(String value1,
				String value2) {
			addCriterion("QUESTIONNAIRE_ID not between", value1, value2,
					"questionnaireId");
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
			addCriterion("QUESTIONSET_ID between", value1, value2,
					"questionsetId");
			return (Criteria) this;
		}

		public Criteria andQuestionsetIdNotBetween(String value1, String value2) {
			addCriterion("QUESTIONSET_ID not between", value1, value2,
					"questionsetId");
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
			addCriterion("QUESTION_ID not between", value1, value2,
					"questionId");
			return (Criteria) this;
		}

		public Criteria andAnswerIsNull() {
			addCriterion("ANSWER is null");
			return (Criteria) this;
		}

		public Criteria andAnswerIsNotNull() {
			addCriterion("ANSWER is not null");
			return (Criteria) this;
		}

		public Criteria andAnswerEqualTo(String value) {
			addCriterion("ANSWER =", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotEqualTo(String value) {
			addCriterion("ANSWER <>", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerGreaterThan(String value) {
			addCriterion("ANSWER >", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerGreaterThanOrEqualTo(String value) {
			addCriterion("ANSWER >=", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLessThan(String value) {
			addCriterion("ANSWER <", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLessThanOrEqualTo(String value) {
			addCriterion("ANSWER <=", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLike(String value) {
			addCriterion("ANSWER like", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotLike(String value) {
			addCriterion("ANSWER not like", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerIn(List<String> values) {
			addCriterion("ANSWER in", values, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotIn(List<String> values) {
			addCriterion("ANSWER not in", values, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerBetween(String value1, String value2) {
			addCriterion("ANSWER between", value1, value2, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotBetween(String value1, String value2) {
			addCriterion("ANSWER not between", value1, value2, "answer");
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
			addCriterion("CREATE_DATE not between", value1, value2,
					"createDate");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationIsNull() {
			addCriterion("CREATE_ORGANIZATION is null");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationIsNotNull() {
			addCriterion("CREATE_ORGANIZATION is not null");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationEqualTo(String value) {
			addCriterion("CREATE_ORGANIZATION =", value, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationNotEqualTo(String value) {
			addCriterion("CREATE_ORGANIZATION <>", value, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationGreaterThan(String value) {
			addCriterion("CREATE_ORGANIZATION >", value, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationGreaterThanOrEqualTo(String value) {
			addCriterion("CREATE_ORGANIZATION >=", value, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationLessThan(String value) {
			addCriterion("CREATE_ORGANIZATION <", value, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationLessThanOrEqualTo(String value) {
			addCriterion("CREATE_ORGANIZATION <=", value, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationLike(String value) {
			addCriterion("CREATE_ORGANIZATION like", value,
					"createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationNotLike(String value) {
			addCriterion("CREATE_ORGANIZATION not like", value,
					"createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationIn(List<String> values) {
			addCriterion("CREATE_ORGANIZATION in", values, "createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationNotIn(List<String> values) {
			addCriterion("CREATE_ORGANIZATION not in", values,
					"createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationBetween(String value1,
				String value2) {
			addCriterion("CREATE_ORGANIZATION between", value1, value2,
					"createOrganization");
			return (Criteria) this;
		}

		public Criteria andCreateOrganizationNotBetween(String value1,
				String value2) {
			addCriterion("CREATE_ORGANIZATION not between", value1, value2,
					"createOrganization");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeIsNull() {
			addCriterion("DICT_VALUE_CODE is null");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeIsNotNull() {
			addCriterion("DICT_VALUE_CODE is not null");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeEqualTo(String value) {
			addCriterion("DICT_VALUE_CODE =", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeNotEqualTo(String value) {
			addCriterion("DICT_VALUE_CODE <>", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeGreaterThan(String value) {
			addCriterion("DICT_VALUE_CODE >", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeGreaterThanOrEqualTo(String value) {
			addCriterion("DICT_VALUE_CODE >=", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeLessThan(String value) {
			addCriterion("DICT_VALUE_CODE <", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeLessThanOrEqualTo(String value) {
			addCriterion("DICT_VALUE_CODE <=", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeLike(String value) {
			addCriterion("DICT_VALUE_CODE like", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeNotLike(String value) {
			addCriterion("DICT_VALUE_CODE not like", value, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeIn(List<String> values) {
			addCriterion("DICT_VALUE_CODE in", values, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeNotIn(List<String> values) {
			addCriterion("DICT_VALUE_CODE not in", values, "dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeBetween(String value1, String value2) {
			addCriterion("DICT_VALUE_CODE between", value1, value2,
					"dictValueCode");
			return (Criteria) this;
		}

		public Criteria andDictValueCodeNotBetween(String value1, String value2) {
			addCriterion("DICT_VALUE_CODE not between", value1, value2,
					"dictValueCode");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PIP_UQS_ANSWER
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PIP_UQS_ANSWER
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}