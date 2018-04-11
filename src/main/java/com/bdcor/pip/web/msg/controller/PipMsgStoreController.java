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
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.msg.domain.PipMsgMsgtmp;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtmpFilter;
import com.bdcor.pip.web.msg.service.PipMsgtmpService;


/**  
 * description:   短信库
 * @author yangfeng 创建时间：2016年5月9日         
 */
@Controller()
@RequestMapping("msg/msgStore")
public class PipMsgStoreController
{	
	@Autowired
	private PipMsgtmpService pipMsgMsgtmp;
	@RequestMapping
	public String init(){
		return "msg/store/list";
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> showEventList(PipMsgMsgtmpFilter filter) { 
		List<Map<String,Object>> data = pipMsgMsgtmp.list(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	@RequestMapping("openmodalForm")
	public String form(Model model,@RequestParam(value="id",required=false)String id){
		if(!StringUtils.isEmpty(id)){
			model.addAttribute("msgTmp", pipMsgMsgtmp.selectById(id));
		}
		return "msg/store/form";
	}
	@RequestMapping("addDate")
	@ResponseBody
	public Map<String,Object> addDate(PipMsgMsgtmp pipMsgtmp){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			pipMsgMsgtmp.insertOrUpdate(pipMsgtmp);
			result.put("success", true);
		} catch (Exception e)
		{
			result.put("success", false);
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(@RequestParam("id")String id){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			pipMsgMsgtmp.delete(id);
			result.put("success", true);
		} catch (Exception e)
		{
			result.put("success", false);
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("getMsgType")
	@ResponseBody
	public List<Map<String, Object>> getMsgType(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list=pipMsgMsgtmp.getMsgType();
		return list;
	}
}

