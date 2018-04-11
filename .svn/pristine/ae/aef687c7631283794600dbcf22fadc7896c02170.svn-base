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
import com.bdcor.pip.web.quality.domain.PipExpImplementProblem;
import com.bdcor.pip.web.quality.domain.PipExpPlan;
import com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter;
import com.bdcor.pip.web.quality.filter.PipExpPlanFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementProblemService;
import com.bdcor.pip.web.quality.service.PipExpPlanService;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-5         
 */
@Controller
@RequestMapping("quality/examineProblem")
public class ExamineProblemController {
	@Autowired
	private PipExpImplementProblemService pipExpImplementProblemService;
	@RequestMapping
	public String init(){
		return "quality/examineProblem/list";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> add(PipExpImplementProblem problem) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			/*boolean checkIsExist = pipExpImplementProblemService.checkIsExist(problem.getLccUserId());
			if(checkIsExist){
				result.put("success", false);
				result.put("msg", "已经添加过此用户！");
				return new ResponseEntity(result, HttpStatus.OK);
			}*/
			pipExpImplementProblemService.insert(problem);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipExpImplementProblem> list(PipExpImplementProblemFilter filter) {
		List<PipExpImplementProblem> list = pipExpImplementProblemService.listByExample(filter);
		JqgridResponse<PipExpImplementProblem> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> update(PipExpImplementProblem problem) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			/*boolean checkIsExist = pipExpImplementProblemService.checkIsExistAndNotSelf(problem.getLccUserId(),problem.getId());
			if(checkIsExist){
				result.put("success", false);
				result.put("msg", "此用户已经存在！");
				return new ResponseEntity(result, HttpStatus.OK);
			}*/
			pipExpImplementProblemService.update(problem);
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
			pipExpImplementProblemService.delete(id);
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
			pipExpImplementProblemService.deleteBatch(ids);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}

