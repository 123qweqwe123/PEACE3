/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.controller 
 */

package com.bdcor.pip.web.msg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;
import com.bdcor.pip.web.msg.domain.PipMsgMsgtmp;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtmpFilter;
import com.bdcor.pip.web.msg.service.PipMsgtmpService;


/**  
 * description:   随访人员管理
 * @author yangfeng 创建时间：2016年5月9日         
 */
@Controller()
@RequestMapping("msg/followVisit")
public class PipFollowVisitController
{	
	@Autowired
	private PatientService patientService;
	@RequestMapping
	public String init(){
		return "msg/followVisit/list";
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatient> queryPatientList(PatientFilter pf) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		pf.setProjectId(projectId);
		pf.setUserId(Securitys.getUserId());
		pf.setIsJoinMsg(1);
		List<PipCommPatient> data = patientService.queryPatientList(pf);
		JqgridResponse<PipCommPatient> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(data);
		return response;

	}
}

