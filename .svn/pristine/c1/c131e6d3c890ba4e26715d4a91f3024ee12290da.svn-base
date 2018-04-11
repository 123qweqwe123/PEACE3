/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.controller 
 */

package com.bdcor.pip.web.msg.controller;

import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.QuestionC;
import com.bdcor.pip.client.vo.paper.QuestionGroup;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.vo.paper.ResultOption;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;
import com.bdcor.pip.web.msg.service.MsgJoinService;
import com.bdcor.pip.web.msg.service.MsgSendRuleService;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.qn.service.impl.AnswerQnlogServiceImpl;
import com.bdcore.webservice.client.SendResultCallback;
import com.bdcore.webservice.client.bean.MsgOfSend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * description:   入选
 * @author yangfeng 创建时间：2016年5月9日         
 */
@Controller()
@RequestMapping("msg/noJoin")
public class MsgNoJoinController{	
	
	@Autowired
	private PatientService patientService;
	
	@RequestMapping
	public String init(HttpServletRequest request){
		request.setAttribute("patientId",request.getParameter("patientId"));
		return "msg/noJoin/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<PipCommPatient> queryPatientList(PatientFilter pf) {
		pf.setProjectId(Securitys.getCurrentProject());
		pf.setUserId(Securitys.getUserId());
		pf.setNoQn("'010'");
		pf.setCompleteQn("'001'");
//		pf.setSuifang("002002");
		List<PipCommPatient> data = patientService.queryPatientList(pf);
		JqgridResponse<PipCommPatient> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
}

