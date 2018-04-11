package com.bdcor.pip.web.spem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.spem.domain.CheckInListVo;
import com.bdcor.pip.web.spem.filter.CheckInListFilter;
import com.bdcor.pip.web.spem.service.CheckInListService;

@Controller
@RequestMapping("spem/checkInList") 
public class CheckInListController {
	@Autowired
	private CheckInListService checkInListService; 
	
	@RequestMapping(value="init",method = RequestMethod.GET)
	public  String  init(Model model){
		return "spem/cheakInList_query";    
	}  
	
	@RequestMapping(value="listQuery",produces="application/json")
	public  @ResponseBody JqgridResponse  listQuery(CheckInListFilter  filter ){  
		List<CheckInListVo>  data = checkInListService.listQuery(filter) ;  
		JqgridResponse<CheckInListVo> response = JqgridResponseContext.getJqgridResponse(); 
		response.setRows(data);     
		return response;     
	}  
	
	@RequestMapping(value="toAdd")
	public String  toAdd(Model model){  
		CheckInListVo checkInList = new CheckInListVo();
		checkInList.setBoxTypes("ABCDEFG");
		model.addAttribute("vo",checkInList);
		return "spem/checkInList_add";
	}
	
	@RequestMapping(value="getCanSelectBoxList",produces="application/json")
	public @ResponseBody List<Map<String,Object>>  getCanSelectBoxList(@RequestParam("fridgeId")String fridgeId,@RequestParam("checkInListId")String checkInListId){
		Map<String,String> selectBoxParamMap = new HashMap<String, String>();
		if(fridgeId != null && fridgeId.trim().length()>0){
			selectBoxParamMap.put("fridgeId", fridgeId);
		}
		if(checkInListId != null && checkInListId.trim().length()>0){
			selectBoxParamMap.put("checkInListId", checkInListId);
		}
		return checkInListService.getCanSelectBoxList(selectBoxParamMap);
	}
	
	@RequestMapping(value="toPreview")
	public String  toPreview(CheckInListVo checkInList,Model model){
		if(checkInList != null){
			checkInList.setProjectId(Securitys.getUser().getCurrent_projectId());
			checkInList.setCreateBy(Securitys.getUser().getName());
		}
		model.addAttribute("vo",checkInList);
		return "spem/checkInList_addPreview";
	}
	
	@RequestMapping(value="backToAdd")
	public String  backToAdd(CheckInListVo checkInList,Model model){
		model.addAttribute("vo",checkInList);
		if(checkInList.getBoxs()==null || checkInList.getBoxs().trim().length()==0){
			checkInList.setBoxs("-100");
		}
		model.addAttribute("boxs",checkInListService.getBoxByBoxIds(checkInList.getBoxs().split(",")));
		return "spem/checkInList_add";
	}
	
	@RequestMapping(value="previewBoxList",produces="application/json")
	public  @ResponseBody List<Map<String,Object>>  previewBoxList(@RequestParam("boxs")String boxs){  
		if(boxs==null||boxs.trim().length()==0){
			boxs = "-100";
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boxs", boxs.split(","));
		List<Map<String,Object>>  dataList = checkInListService.previewBoxList(paramMap);  
		return dataList;     
	} 
	
	@RequestMapping(value="previewTubeList",produces="application/json")
	public  @ResponseBody List<Map<String,Object>>  previewTubeList(@RequestParam("boxs")String boxs){  
		if(boxs==null||boxs.trim().length()==0){
			boxs = "-100";
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boxs", boxs.split(","));
		List<Map<String,Object>>  dataList = checkInListService.previewTubeList(paramMap);  
		return dataList;     
	} 
	
	@RequestMapping(value="save")
	public ModelAndView  save(CheckInListVo checkInList){
		int i = checkInListService.save(checkInList);
		return new ModelAndView("spem/cheakInList_query");
	}
	
	@RequestMapping(value="showDetail")
	public String  showDetail(@RequestParam("checkInListId")String id,Model model){
		model.addAttribute("vo",checkInListService.getCheckInListVoById(id));
		return "spem/checkInList_showDetail";
	}
	
	@RequestMapping(value="toModify")
	public String  toModify(@RequestParam("checkInListId")String id,Model model){
		CheckInListVo vo = checkInListService.getCheckInListVoById(id);
		if(vo != null){
			vo.setBoxTypes("ABCDEFG");
			model.addAttribute("boxs",checkInListService.getBoxBycheckInListId(id));
		}
		model.addAttribute("vo",vo);
		return "spem/checkInList_modify";
	}
	
	@RequestMapping(value="modify")
	public ModelAndView  modify(CheckInListVo checkInList){
		int i = checkInListService.modify(checkInList);
		return new ModelAndView("spem/cheakInList_query");
	}
} 
