package com.bdcor.pip.web.msg.controller;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.fee.filter.FeeFilter;
import com.bdcor.pip.web.msg.filter.MsgSendFilter;
import com.bdcor.pip.web.msg.service.MsgSendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("msg/report")
public class MsgReportController {
	
	@Autowired
	private MsgSendService msgSendService;
	
	@RequestMapping
	public String init(){
		return "msg/report/list";
	}
	
	@RequestMapping(value = "list", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String, Object>> list(MsgSendFilter filter){
		List<Map<String, Object>> msgSendList = msgSendService.report(filter);
		JqgridResponse<Map<String, Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(msgSendList);
        return response;
	}
	
	@RequestMapping(value = "openmodalfailReason", produces = MediaType.APPLICATION_JSON_VALUE)
	public String failReason(){
        return "msg/report/failReason";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="getFailList",produces="application/json")
	public @ResponseBody Map getFailList(MsgSendFilter filter){
		List<Map<String, Object>> msgSendList = msgSendService.failReasorList(filter);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", msgSendList);
		
		return map;  
	} 
}
