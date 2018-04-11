package com.bdcor.pip.web.qn.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilterNoBase;


@MyBatisRepository
public interface AnswerQnDao {

	Map<String, String> getAnswerLogMap(Map<String, String> map);
	
	Map<String, String> getAnswerLogMap_MIN(Map<String, String> map);
	
	void insertAnswerQnLog(Map<String, Object> answerQnLog);

	void clearUqsAnswer(Map<String, String> clearAnswerMap);

	void insertAnswer(Map<String, Object> aMap);

	List<Map<String, Object>> getPatientList(PatientFilter filter);

	List<Map<String, Object>> exportPatientList(PatientFilter filter);

	void answerToDropMap(Map<String, String> answerToDropMap);

	void updatePatientBlood(Map<String, String> updateBloodMap);

	void insertAnswerQnLog_MIN(Map<String, Object> answerQnLog);

	void updateAnswerQnLog_MIN(Map<String, Object> answerQnLog);

	void insertAnswer_MIN(Map<String, Object> aMap);

	Map<String, Object> checkBloodNo(Map<String, Object> paramMap);

	void insertPlanDate(Map<String, Object> planDateMap);

	void updateLastDate(Map<String, Object> updateLastDatePMap);

	Map<String,Object> getDatePatient(Map<String, Object> planDateMap);

	void updatePlanDate(Map<String, Object> planDateMap);

	void insertDataRemark(Map<String, Object> pdMap);

	void updatePatientInfo(Map<String, Object> patientMap);

	List<Map<String, Object>> listBlood(ScmSearchFilter filter);
	List<Map<String, Object>> listBloodNoPage(ScmSearchFilterNoBase filter);

	int selectUqsLog(Map<String, Object> param);

	String getCurrentVersion(String versionStart);

	List<Map<String, String>> getUqsAnswer(Map<String, Object> pMap);

	Map<String,Object> getLastUqs(String patientId);
}
