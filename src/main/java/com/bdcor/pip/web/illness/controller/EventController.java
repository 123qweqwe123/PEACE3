/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.illness.controller 
 */

package com.bdcor.pip.web.illness.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.illness.filter.EventFilter;
import com.bdcor.pip.web.illness.service.EventService;
/**  
 * description:  
 * @author yangfeng 创建时间：2016年3月18日         
 */
@Controller
@RequestMapping(value = "illness/event")
public class EventController{
	@Autowired
	private EventService eventService;
	@RequestMapping
	public String init(Model model){
		model.addAttribute("hisList",eventService.getHisList());
		return "illness/event/list"; 	
	}
	/**
	 * 
	 * description:  事件查看列表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月18日
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> showEventList(EventFilter filter) { 
		List<Map<String,Object>> data = eventService.list(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
}

