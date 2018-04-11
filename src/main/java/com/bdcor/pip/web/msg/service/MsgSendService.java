package com.bdcor.pip.web.msg.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.msg.domain.MsgSendVo;
import com.bdcor.pip.web.msg.filter.MsgSendFilter;
import com.bdcor.pip.web.msg.filter.MsgSendNoFilter;


public interface MsgSendService {

	/**
	 * 所有发送短信回执的状态码 及状态码对应的说明
	 * @return
	 */
	List<Map<String,String>> getReportCode();
	
	List<MsgSendVo> list(MsgSendFilter filter);

	List<Map<String, String>> getPatient(String lccCode);

	List<Map<String, String>> getMsgTypeList();

	List<Map<String, String>> getMsgListByType(String typeCode);

	int sendSave(Map<String, Object> sendMap);

	int sendDelete(String[] split);
	
	void send();

	void testSend(String mobile);

	List<Map<String, Object>> report(MsgSendFilter filter);

	List<Map<String, Object>> sendReport(MsgSendNoFilter filter);

	List<Map<String, Object>> failReasorList(MsgSendFilter filter);
}
	