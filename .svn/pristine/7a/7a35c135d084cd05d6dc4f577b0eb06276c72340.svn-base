package com.bdcor.pip.web.qn.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.EventVo;
import com.bdcor.pip.web.qn.filter.EventExportFilter;
import com.bdcor.pip.web.qn.filter.EventFilter;


@MyBatisRepository
public interface UqsEventDao {
	
	List<Map<String, String>> getEventTemMapList(Map<String, Object> paramMap);

	void insertEvent(Map<String, Object> insertEventMap);

	List<EventVo> getEventPatientList(EventFilter filter);

	List<Map<String, Object>> showEventList(EventFilter filter);

	List<Map<String, Object>> showUsrFileList(EventFilter filter);
	List<Map<String, Object>> showAllUsrFileList(EventFilter filter);
	
	void insertEventUsrFile(Map<String, Object> map);

	List<Map<String, String>> getDictFiles();

	List<Map<String, String>> getHisList();

	void clearEvent(Map<String, String> answerToDropMap);

	List<Map<String, Object>> showReportList(EventFilter filter);

	void updateEvent(Map<String, Object> insertEventMap);

	void updatePDead(String patientId);

	void updateUsrFile(Map<String, Object> updateEventMap);

	List<Map<String, Object>> getEventList(EventFilter filter);

	List<Map<String, Object>> getEventListExcel(EventExportFilter filter);

	void updateEventUsrFileState(Map<String, Object> map);

}
