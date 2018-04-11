/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.controller 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.controller  
 */

package com.bdcor.pip.web.quality.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.quality.domain.PipExpPlan;
import com.bdcor.pip.web.quality.filter.PipExpPlanFilter;
import com.bdcor.pip.web.quality.service.PipExpPlanService;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-5         
 */
@Controller
@RequestMapping("quality/examinePlan")
public class ExaminePlanController {
	@Autowired
	private PipExpPlanService pipExpPlanService;
	@RequestMapping
	public String init(){
		return "quality/examinePlan/list";
	}
	@RequestMapping(value = "openmodaladdPlaninput", method = RequestMethod.GET)
	public String openmodaladdPlaninput(Model model ,@RequestParam(value = "id", required = false) String id){
		if(!StringUtils.isEmpty(id)){
			PipExpPlan plan =pipExpPlanService.selectExpPlan(id);
			model.addAttribute("plan", plan);
		}
		return "quality/examinePlan/form";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addPipExpPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addPipExpPlan(PipExpPlan plan) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpPlanService.insert(plan);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipExpPlan> list(PipExpPlanFilter filter) {
		List<PipExpPlan> list = pipExpPlanService.listByExample(filter);
		JqgridResponse<PipExpPlan> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> update(PipExpPlan plan) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpPlanService.update(plan);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestParam("id")String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpPlanService.delete(id);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteBatch(@RequestParam("ids")String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpPlanService.deleteBatch(ids);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}

