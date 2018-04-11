/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.service 
 */

package com.bdcor.pip.web.msg.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.web.msg.domain.PatientGroupVo;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年6月13日         
 */
public interface MsgJoinService
{
	public void saveQn(HttpServletRequest request,Paper paper) throws Exception;

	public Map<String, String> getAnswerLogMap(String substring, String patientId);

	public Paper getPaperByPath(String path);

	public boolean isExistUqs(Map<String, Object> param);
	public void patientGroup(PatientGroupVo gpVo) throws Exception;

	public String getCurrentVersion(String string);

	public List<Map<String, String>> getUqsAnswer(String patientId,
			String string);

	public int sendWelMsg(String patientId, String mobile, String msgTypeCode);

	public int getSendCount(String patientId, String mobile, String msgTypeCode);

	public Map<String,Object> checkGroupInfo(String pid);

	public String getProcesstype(String patientId);

	Map<String,Object> getPatientInfo(String patientId);
	}

