package com.bdcor.pip.web.msg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.msg.domain.MsgTdVo;
import com.bdcor.pip.web.msg.filter.MsgTdFilter;
import com.bdcor.pip.web.msg.service.MsgTdService;

@Controller
@RequestMapping("msg/td")
public class MsgTdController {
	
	@Autowired
	private MsgTdService msgTdService;
	
	@RequestMapping
	public String init(){
		return "msg/td/list";
	}
	
	@RequestMapping(value = "list", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<MsgTdVo> list(MsgTdFilter filter){
		List<MsgTdVo> msgTdList = msgTdService.list(filter);
		if(msgTdList != null && msgTdList.size()>0){
			for(int i=0;i<msgTdList.size();i++){
				msgTdList.get(i).setId(i+"");
			}
		}
		JqgridResponse<MsgTdVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(msgTdList);
        return response;
	}
	
	/**
	 * description: 审核通过
	 */
	@RequestMapping(value = "audits", produces = "application/json")
	public @ResponseBody ResponseEntity audits(@RequestParam(value="pIds",required=true)String pIds) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			int i = 0;
			i =  msgTdService.audits(pIds.split(","),2);
			if(i>0){
				res.put("success", true);
			}else{
				res.put("success", false);
			}
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
	
}
