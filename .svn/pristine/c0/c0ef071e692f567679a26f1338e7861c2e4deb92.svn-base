package com.bdcor.pip.web.quality.controller;

import java.util.List;	
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.quality.domain.QuestionnaireCondition;
import com.bdcor.pip.web.quality.filter.QuestionnaireConditionFilter;
import com.bdcor.pip.web.quality.service.QuestionnaireConditionService;

@Controller
@RequestMapping("quality/questionnaireCondition")
public class QuestionnaireConditionController {
	
	@Autowired
	private QuestionnaireConditionService questionnaireConditionService;
	
	@RequestMapping
	public String init(){
		return "quality/questionnaireCondition/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<QuestionnaireCondition> getQuestionnaireList(QuestionnaireConditionFilter filter) throws Exception{
		//判断输入的是医院名称还是LCCID
		Pattern pattern = Pattern.compile("[0-9]*");
		String str = filter.getLccCode();
	    if(str!=null && !"".equals(str) && !pattern.matcher(str).matches()){
	    	filter.setLccName(str);
	    	filter.setLccCode(null);
	    }
		List<QuestionnaireCondition> list = questionnaireConditionService.getQuestionnaireList(filter);
		JqgridResponse<QuestionnaireCondition> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(list); 
        return response;
	}
}
