package com.bdcor.pip.web.quality.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.domain.CodeAbnormalVo;
import com.bdcor.pip.web.quality.domain.PatientQn;
import com.bdcor.pip.web.quality.filter.CodeAbnormalFilter;
import com.bdcor.pip.web.quality.service.CodeAbnormalService;

@Controller
@RequestMapping("quality/dataAbnormal/codeAbnormal")
public class CodeAbnormalController {
	
	@Autowired
	private CodeAbnormalService codeAbnormalService;
	
	@RequestMapping
	public String init(){
		return "quality/dataAbnormal/codeAbnormal/list";
	}
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<CodeAbnormalVo> list(CodeAbnormalFilter filter){
		filter.setIsReport(2);
		filter.setIsHandle(2);
		List<CodeAbnormalVo> codeAbnormalList = codeAbnormalService.list(filter);
		JqgridResponse<CodeAbnormalVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(codeAbnormalList);
        return response;
	}
	
	@RequestMapping(value = "report")
	public  String report(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		String s = request.getParameter("reportInfo");
		String t = request.getParameter("id");
		//<img src="ftp://username:password@192.168.0.1:21/11/xx.jpg">
        return "quality/dataAbnormal/codeAbnormal/list";
	}
	
	
	@RequestMapping(value = "errorHandle")
	public String errorChangeInit(){
		return "quality/dataAbnormal/codeAbnormal/errorHandleList";
	}
	
	@RequestMapping(value = "errorHandleList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<CodeAbnormalVo> errorHandleList(CodeAbnormalFilter filter){
		filter.setIsHandle(2);
		List<CodeAbnormalVo> codeAbnormalList = codeAbnormalService.list(filter);
		JqgridResponse<CodeAbnormalVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(codeAbnormalList);
        return response;
	}
	
	@RequestMapping(value = "openmodalShowReport", method = RequestMethod.GET)
	public String showReport(Model model,@RequestParam("id")String id){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("qcId",id);
		Map<String,String> reportInfoMap = codeAbnormalService.getReportInfo(paramMap);
		model.addAttribute("reportInfo", reportInfoMap);
		return "quality/dataAbnormal/codeAbnormal/showReport";
	}
	
	@RequestMapping(value = "errorHandle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity errorHandle(@RequestParam("qcId")String qcId,@RequestParam("handleType")String handleType,@RequestParam("handleInfo")String handleInfo){
		Map<String,Object> res = new HashMap<String, Object>();
		if(StringUtils.isBlank(qcId)){
			res.put("success", false);
			 res.put("msg", "id为空，操作失败！");
			return new ResponseEntity(res, HttpStatus.OK);
		}
		
		if(!"1".equals(handleType)&&!"2".equals(handleType)){
			 res.put("success", false);
			 res.put("msg", "处理类型错误，操作失败！");
			 return new ResponseEntity(res, HttpStatus.OK);
		}
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("qcId",qcId);
		paramMap.put("handleType",handleType);
		paramMap.put("handleInfo",handleInfo);
		paramMap.put("handleBy",Securitys.getUser().getId());

		int i = codeAbnormalService.errorHandle(paramMap);
		if(i>0){
			res.put("success", true);
		}else{
			res.put("success", false);
			res.put("msg", "操作失败！");
		}
        return new ResponseEntity(res, HttpStatus.OK);
	}
	
}
