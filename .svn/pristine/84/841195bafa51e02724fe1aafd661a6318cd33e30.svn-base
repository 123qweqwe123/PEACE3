package com.bdcor.pip.web.quality.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.web.quality.domain.PatientQn;
import com.bdcor.pip.web.quality.filter.PatientQnFilter;

public interface PreviewQuestionnaireService {

	List<PatientQn> getPatientQnList(PatientQnFilter filter);

	List<Map<String,Object>> getQnList(Map<String, String> map);

	Paper getPaperByPath(String path);

	Map<String, String> getAnswerMap(String questionnaireId, String patientId,String logId);

    Map<String, String> getAnswerWithDict(Paper qn, Map<String, String> resultOptionMap);
}
