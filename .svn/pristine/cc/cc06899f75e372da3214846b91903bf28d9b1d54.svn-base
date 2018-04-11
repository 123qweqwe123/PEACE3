package com.bdcor.pip.web.qn.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.qn.filter.PatientReportFilter;
import com.bdcor.pip.web.qn.service.PatientReportService;

@Controller
@RequestMapping("qn/patientReport") 
public class PatientReportController {
	
	@Autowired
	private PatientReportService patientReportService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping
	public String init(HttpServletRequest request){
		return "qn/patientReport/list";
	}
	
	/**
	 * 事件管理_人员列表
	 */
	@RequestMapping(value = "getPatientReportList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,String>> getPatientReportList(PatientReportFilter filter) { 
		List<Map<String,String>> data = patientReportService.getPatientReportList(filter);
		JqgridResponse<Map<String,String>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
	
}
