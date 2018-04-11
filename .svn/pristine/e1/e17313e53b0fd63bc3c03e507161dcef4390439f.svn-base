package com.bdcor.pip.web.qn.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilterNoBase;

public interface AnswerQnService {

	Paper getPaperByPath(String path);

	Map<String, String> getAnswerLogMap(String uqsCode, String patientId);
	
	String getDictName(Paper qn, String questionSetId, String questionId,String resultId, String resutlStr);

	void saveQn(HttpServletRequest request, Paper qn)throws Exception;

	List<Map<String, Object>> getPatientList(PatientFilter filter);

	List<Map<String, Object>> exportPatientList(PatientFilter filter);

	Map<String, String> getAnswerLogMap_MIN(String uqsCode, String patientId, String logId);

	void saveQn_MIN(HttpServletRequest request, Paper qn, String logId)throws Exception;

	Map<String, Object> checkBloodNo(String bloodNo, String lccCode);
	/**
	 * 
	 * description: 获取已经收集的采血包的样本信息
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年1月21日
	 */
	List<Map<String, Object>> listBlood(ScmSearchFilter filter);

	List<Map<String, Object>> listBlood(ScmSearchFilterNoBase filter);

	boolean isExistUqs(Map<String, Object> param);

}
	