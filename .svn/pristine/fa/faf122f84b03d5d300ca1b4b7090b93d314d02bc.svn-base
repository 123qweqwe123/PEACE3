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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblem;
import com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementProblemService;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-17         
 */
@Controller
@RequestMapping("quality/examineImplementTrack")
public class ExpImplementTrackController {
	@Autowired
	private PipExpImplementProblemService pipExpImplementProblemService;
	@RequestMapping
	public String init(){
		return "quality/examineTrack/list";
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipExpImplementProblem> list(PipExpImplementProblemFilter filter) {
		List<PipExpImplementProblem> list = pipExpImplementProblemService.list(filter);
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
			boolean checkIsExist = pipExpImplementProblemService.checkIsExistAndNotSelf(problem.getLccUserId(),problem.getId());
			if(checkIsExist){
				result.put("success", false);
				result.put("msg", "此用户已经存在！");
				return new ResponseEntity(result, HttpStatus.OK);
			}
			pipExpImplementProblemService.update(problem);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateStatus(PipExpImplementProblem problem) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpImplementProblemService.updateStatus(problem);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

}

