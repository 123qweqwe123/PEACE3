package com.bdcor.pip.web.msg.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.msg.domain.MsgReplyVo;
import com.bdcor.pip.web.msg.filter.MsgReplyFilter;


public interface MsgReplyService {

	List<MsgReplyVo> list(MsgReplyFilter filter);

	Map<String,Object> getPatient(String lccCode, String patientId);

	List<Map<String, Object>> showAllMsg(String lccCode, String patientId);

	List<Map<String, Object>> getMaplist(MsgReplyFilter filter);

}
	