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
import com.bdcor.pip.web.spem.filter.FrozenTubeFilter;
import com.bdcor.pip.web.spem.service.FrozenTubeService;

@Controller
@RequestMapping("spem/frozenTube") 
public class FrozenTubeController {
	@Autowired
	private FrozenTubeService frozenTubeService; 
	
	@RequestMapping(value="init",method = RequestMethod.GET)
	public  String  init(Model model){
		return "spem/tubeQuery";    
	}  
	
	@RequestMapping(value="tubeList",produces="application/json")
	public  @ResponseBody JqgridResponse  boxQuery(FrozenTubeFilter  filter ){  
		List<FrozenTubeVo>  data = frozenTubeService.spTubeListByfilter(filter) ;  
		for(int i=0;i<data.size();i++){//前台jqgrid会自动将自动将json串中的id给设置为主键
			data.get(i).id=i+"";
		}
		JqgridResponse<FrozenTubeVo> response = JqgridResponseContext.getJqgridResponse(); 
		response.setRows(data);     
		return response;     
	}  
	
} 
