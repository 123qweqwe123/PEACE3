package com.bdcor.pip.web.spem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.spem.domain.FrozenTubeVo;
import com.bdcor.pip.web.spem.domain.ScmStaticVo;
import com.bdcor.pip.web.spem.filter.FrozenTubeFilter;
import com.bdcor.pip.web.spem.filter.ScmStaticFilter;
import com.bdcor.pip.web.spem.service.FrozenTubeService;
import com.bdcor.pip.web.spem.service.ScmStaticService;

@Controller
@RequestMapping("spem/scmStatic") 
public class ScmStaticController {
	@Autowired
	private ScmStaticService scmStaticService; 
	
	@RequestMapping(value="init",method = RequestMethod.GET)
	public  String  init(Model model){
		return "spem/scmStatic";    
	}  
	
	@RequestMapping(value="list",produces="application/json")
	public  @ResponseBody JqgridResponse  scmStatic(ScmStaticFilter  filter ){  
		List<ScmStaticVo>  data = scmStaticService.scmStatic(filter) ;  
		JqgridResponse<ScmStaticVo> response = JqgridResponseContext.getJqgridResponse(); 
		response.setRows(data);     
		return response;     
	}  
	
} 
