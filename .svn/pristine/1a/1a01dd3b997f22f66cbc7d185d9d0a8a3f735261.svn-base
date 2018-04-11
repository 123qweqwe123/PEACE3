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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.quality.domain.PipExpImplement;
import com.bdcor.pip.web.quality.domain.PipExpImplementPerson;
import com.bdcor.pip.web.quality.filter.PipExpImplementPersonFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementPersonService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-11
 */
@Controller
@RequestMapping("quality/examineImplementPerson")
public class ExpImplementPersonController {
	@Autowired
	private PipExpImplementPersonService personService;

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipExpImplementPerson> list(
			PipExpImplementPersonFilter filter) {
		JqgridResponse<PipExpImplementPerson> response = JqgridResponseContext
				.getJqgridResponse();
		List<PipExpImplementPerson> listByExample = null;
		try {
			listByExample = personService.listByExample(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setRows(listByExample);
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> add(PipExpImplementPerson pipExpImplementPerson) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PipExpImplementPerson flag=personService.selectByLccCodeUserCode(pipExpImplementPerson);
			if(null !=flag ){
				result.put("success", false);
				result.put("msg","不能重复提交相同人员！" );
				return new ResponseEntity(result, HttpStatus.OK);
			}
			personService.save(pipExpImplementPerson);
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
			personService.deleteBatch(ids);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
