package com.bdcor.pip.web.dict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.dict.domain.DictMetaDataHandle;
import com.bdcor.pip.dict.domain.DictMetaDataVo;
import com.bdcor.pip.dict.service.DictMetaDataService; 

@Controller
@RequestMapping("/meta")
public class DictMetaDataController {
	
	@Resource
	private DictMetaDataService dictMetaDataService;
	
	@RequestMapping("toMetaDataPage")
	public String toMetaDataPage(){
		return "dict/dictMetaDataManager";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="metadatalist", produces="application/json")
	@ResponseBody
	public JqgridResponse getAllMetaData(DictMetaDataHandle handle){
		List<DictMetaDataVo> list = this.dictMetaDataService.getAllMetaDate(handle);
		JqgridResponse<DictMetaDataVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
	@RequestMapping(value = "updateMetaData")	
	@ResponseBody
	public ResponseEntity updateMetaData(Model model,DictMetaDataVo metaVo, String oper){
		int result = 0;
		String msg = "操作失败";
		Map<String, Object> res = new HashMap<String, Object>();
		
		try {
			if("edit".equals(oper)){
				metaVo.setIsRequired(StringUtils.isEmpty(metaVo.getIsRequired())?"":metaVo.getIsRequired());
				result = dictMetaDataService.updateMetaData(metaVo);
				msg = "更新成功";
			}
			//else if("add".equals(oper)){
			//	try{
			//	result = dictMetaDataService.addMetaData(metaVo);}catch(Exception e){e.printStackTrace();}
			//	msg = "添加失败";
			//}
			else if("del".equals(oper)){
				metaVo.setIsRemoved("1");
				result = dictMetaDataService.delMetaData(metaVo);
				msg = "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1){
			model.addAttribute("errMsg", msg);
		}
		
		if(result!=1){
			 res.put("success", false); 
			 res.put("errMsg", "操作失败");
			 return new ResponseEntity(res, HttpStatus.FAILED_DEPENDENCY);
		} else {
			res.put("errMsg", msg);
			res.put("success", true); 
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
	
}
