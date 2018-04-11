/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.controller 
 */

package com.bdcor.pip.web.msg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.web.msg.service.MsgSendRuleService;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年5月16日         
 */
@Controller
@RequestMapping("msg/rule")
public class MsgSendRuleController
{
	@Autowired
	private MsgSendRuleService msgSendRuleService;
	@RequestMapping("start")
	public String init(){
		msgSendRuleService.needSendMsg();
		return "";
	}
	@RequestMapping("send")
	@ResponseBody
	public Map<String, Boolean> send(){
		Map<String,Boolean> result = new HashMap<String,Boolean>();
		try
		{
			msgSendRuleService.send();
			result.put("SUCCESS", true);
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("SUCCESS", false);
		}
		return result;
	}
}

