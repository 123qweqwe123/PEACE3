/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.sys.version.controller 
 */

package com.bdcor.pip.web.sys.version.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.client.tools.DateUtils;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.sys.version.domain.PipSysVersion;
import com.bdcor.pip.web.sys.version.filter.PipSysVersionFilter;
import com.bdcor.pip.web.sys.version.service.VersionService;

import java_cup.runtime.virtual_parse_stack;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年4月20日         
 */
@Controller
@RequestMapping("/version")
public class VersionController
{	
	@Autowired
	private VersionService versionService;
	@RequestMapping
	public String init(){
		return "version/list";
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> getPatientList(PipSysVersionFilter filter) { 
		List<Map<String,Object>> data = versionService.list(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	@RequestMapping(value="openmodalForm")
	public String openmodalShowEvent(HttpServletRequest request){
		String version  = request.getParameter("version") == null ? "":request.getParameter("version");
		if(StringUtils.isEmpty(version)){
			request.setAttribute("versionCode",DateUtils.getOrderNoByNowDay());
			request.setAttribute("edit",false);
			
		}
		else{
			PipSysVersion pipSysVersion =versionService.selectVersion(version);
			request.setAttribute("version",pipSysVersion);
			request.setAttribute("edit",true);
		}
		return "version/form";
	}
	@RequestMapping(value="addDate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDate(PipSysVersion version){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			versionService.addDate(version);
			result.put("success", true);
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("success", false);
		}
		return result;
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteData(@RequestParam("version")String version){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			versionService.deleteDate(version);
			result.put("success", true);
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("success", false);
		}
		return result;
	}
	@RequestMapping(value="updateDate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDate(PipSysVersion version){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			versionService.update(version);
			result.put("success", true);
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("success", false);
		}
		return result;
	}
}

