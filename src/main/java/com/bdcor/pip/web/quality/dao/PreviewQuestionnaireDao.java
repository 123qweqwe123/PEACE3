package com.bdcor.pip.web.quality.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.PatientQn;
import com.bdcor.pip.web.quality.filter.PatientQnFilter;

@MyBatisRepository
public interface PreviewQuestionnaireDao {
	
	List<PatientQn> getPatientQnList(PatientQnFilter filter);
	
	List<Map<String, Object>> getQnList(Map<String, String> map);

	List<Map<String, String>> getAnswerList(Map<String, String> paramMap);

	String getDictName_PUB(Map<String, String> pMap);

	String getDictName_DISTRICT(Map<String, String> pMap);

	String getDictName_PI(Map<String, String> pMap);

	String getDictName_ICD(Map<String, String> pMap);

}
