package com.bdcor.pip.web.qn.service;

import com.bdcor.pip.web.common.domain.PipSysParameter;
import com.bdcor.pip.web.qn.domain.*;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import com.bdcor.pip.web.qn.filter.EventExportFilter;
import com.bdcor.pip.web.qn.filter.EventFilter;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;


public interface EventMgtService {
	
	List<EventVo> getEventPatientList(EventFilter filter);

	List<Map<String, Object>> showEventList(EventFilter filter);

	List<Map<String, Object>> showUsrFileList(EventFilter filter);
	List<Map<String, Object>> showAllUsrFileList(EventFilter filter);
	void collectDictFile(String logMinId, String patientId, String fileInfo, String inHosDate,String outHosDate,String hosName)throws Exception;

	List<Map<String, String>> getDictFiles();

	List<Map<String,String>> getHisList();

	List<Map<String, Object>> showReportList(EventFilter filter);

	List<Map<String, Object>> getEventList(EventFilter filter);

	List<Map<String, Object>> getEventListExcel(EventExportFilter filter);


    List<PipCommEventVO> getEventCheckList(EventCheckFilter filter);

    void getEventInfo(String eventCode, Model model);

    List<PipSysParameter> getCurrdesctree();

    void saveOrUpdate(PipCommEventCheck check, PipCommEventCheckEr er);

    PipCommEventCheckEr getErByEventCode(String eventCode);

	boolean checkAuditUser();

	/**
	 * 添加审核用户
	 */
	boolean addAuditUser();

	String addEvent(String eventCode, Model model);

    List<PipCommEventExportVO> getEvent2exportList(EventCheckFilter filter);

	String updateEventCheckStatus(String eventCodeList);
}
	