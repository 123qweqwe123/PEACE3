package com.bdcor.pip.web.dict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.dict.domain.ColumnVo; 
import com.bdcor.pip.dict.domain.RequestHandle;
import com.bdcor.pip.dict.service.DictManagerService;
import com.bdcor.pip.web.common.service.CommonJdbcService;

@SuppressWarnings("restriction")
@Controller
@RequestMapping("sys/dict")
public class DictManageController {  

	@Resource
	private DictManagerService dictService; 
	
	//@Resource
	//private CommonJdbcService  commonJdbcService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="grid_init",method = RequestMethod.GET)
	public String grid(Model model,String tableName,String dictTypeCode,String typeCode){
		
		/*List allDict = this.dictService.getAllDICT(typeCode);
		
		model.addAttribute("dictList",allDict); 
		model.addAttribute("typeCode", typeCode);	
		if( !StringUtils.isEmpty(tableName) )
		{
			//String condition = this.dictService.getQueryerCondition(tableName);
			//model.addAttribute("condition", condition);
			model.addAttribute("tableName",tableName);
			model.addAttribute("dictTypeCode",dictTypeCode);
		}*/
		return "dict/dictManagerCommon";
 } 
	
@RequestMapping(value="dict_list",produces="application/json")
public @ResponseBody List dict_list(String typeCode){
		
		List allDict = this.dictService.getAllDICT(typeCode);
		
		return allDict;
} 
		
@RequestMapping(value="getColumn",produces="application/json")
public  @ResponseBody Map getJqGridDataColumn(String tableName){
	
	if(tableName==null|| "".equals(tableName))
		return null;
	Map map = this.dictService.getJqGridColumns(tableName);
	
	return map; 
} 

@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping(value="getData",produces="application/json")
public  @ResponseBody JqgridResponse getShowData(RequestHandle  handle,HttpServletRequest request){
	
	request.getParameter("ceshi");
	if( StringUtils.isEmpty(handle.getFilters() ))
		handle.setFilters(request.getParameter("filters"));
	List cols = this.dictService.getDictData(handle);
	JqgridResponse<Map> response = JqgridResponseContext.getJqgridResponse();
	response.setRows(cols);
	
	return response;
 }

@RequestMapping(value="getAllDict",produces="application/json")
public  @ResponseBody List getAllDict(){
	
	
	
	return null;
}

@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value = "edit", method=RequestMethod.POST)	
@ResponseBody
public  ResponseEntity editDict(Model model,HttpServletRequest request, String oper){
	String tableName = request.getParameter("tableName");
	
	List<ColumnVo> cols = this.dictService.getTableStruct(tableName);
	String id = request.getParameter("id");
	Map map = new HashMap();
	
	for(ColumnVo vo : cols){
		String colName = vo.getColumnName();
		String value = request.getParameter(colName);
		if(!StringUtils.isEmpty(value) ){
			map.put(colName, value);
		}
	}  
	
	int result = 0;
	String msg = "操作成功";
	if("edit".equals(oper)){
		
		result = dictService.updateData(map,tableName,id); 
		//msg = "更新失败";
	}
	else if("add".equals(oper)){
		
		result = dictService.addData(map,tableName);
		//msg = "添加失败";
	}
	Map<String, Object> res = new HashMap<String, Object>();
		/*else if("del".equals(oper)){
		
		result = dictService.delData( id);
		msg = "删除失败";
	} */
	if(result!=1){
		//model.addAttribute("errMsg", msg);
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
