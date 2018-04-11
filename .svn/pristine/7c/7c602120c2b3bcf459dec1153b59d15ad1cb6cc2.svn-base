package com.bdcor.pip.web.data.controller;

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
import com.bdcor.pip.web.data.filter.PatientNameModifyFilter;
import com.bdcor.pip.web.data.service.PatientNameModifyService;

@Controller
@RequestMapping("dataMgt/patientNameModify")
public class PatientNameModifyController {
	
	@Autowired
	private PatientNameModifyService patientNameModifyService;
	
	@RequestMapping
	public String init(){
		return "dataMgt/patientNameModify/list";
	}
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> list(PatientNameModifyFilter filter){
		List<Map<String,Object>> list = patientNameModifyService.list(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(list);
        return response;
	}
	
	
	@RequestMapping(value = "save")
	public @ResponseBody ResponseEntity save(@RequestParam("patientId")String patientId,@RequestParam("oldPatientName")String oldPatientName,@RequestParam("newPatientName")String newPatientName){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try{
			patientNameModifyService.save(patientId,oldPatientName,newPatientName);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("msg","保存失败");
			if(e.getMessage()!=null && e.getMessage().trim().length()>0){
				result.put("msg",e.getMessage().trim());
			}
			return new ResponseEntity(result, HttpStatus.OK);
		}
		result.put("success", true);
        return new ResponseEntity(result, HttpStatus.OK);
	}
	
}
