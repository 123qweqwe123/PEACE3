package com.bdcor.pip.web.scm.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.domain.CodeAbnormalVo;
import com.bdcor.pip.web.quality.filter.CodeAbnormalFilter;
import com.bdcor.pip.web.scm.filter.ScmFilter;
import com.bdcor.pip.web.scm.service.ScmService;

/**
 * 做样本
 */
@Controller
@RequestMapping("scm")
public class ScmController {
	@Autowired
	private ScmService scmlService;
	
	@RequestMapping(value="index")
	public String init(){
		return "scm/index";
	}
	
	@RequestMapping(value="toDoSample")
	public String toDoSample(@RequestParam(value="boxCode")String boxCode,Model model){
		Map<String,String> boxMap = scmlService.getBoxMap(boxCode);
		Map<String,String> tubeCodeMap = new HashMap<String, String>();
		List<Map<String,String>> tubeRemarkList = new ArrayList<Map<String,String>>();
		
		if(boxMap != null){
			List<Map<String,String>> tubeList = scmlService.getTubeList(boxCode);
			if(tubeList != null && tubeList.size()>0){
				for(Map<String,String> map:tubeList){
					if("1".equals(map.get("BOX_COLNO"))){
						tubeCodeMap.put("tubeCode"+map.get("BOX_ROWNO"),map.get("TUBE_CODE"));
					}
					
					if(map.get("REMARK") != null && map.get("REMARK").trim().length()>0){
						Map<String,String> tubeRemarkMap = new HashMap<String,String>();
						tubeRemarkMap.put("remark", map.get("REMARK").trim());
						tubeRemarkMap.put("rowno", map.get("BOX_ROWNO"));
						tubeRemarkMap.put("colno", map.get("BOX_COLNO"));
					}
				}
			}
		}
		model.addAttribute("boxCode",boxCode);
		model.addAttribute("boxMap",boxMap);
		model.addAttribute("tubeCodeMap",tubeCodeMap);
		model.addAttribute("tubeRemarkList",tubeRemarkList);
		return "scm/main";
	}
	/**
	 * 盒列表
	 */
	@RequestMapping(value = "boxList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> boxList(ScmFilter filter){
		List<Map<String,Object>> boxList = scmlService.boxList(filter);
		if(boxList != null && boxList.size()>0){
			for(int i=0;i<boxList.size();i++){
				boxList.get(i).put("id", i+"");
			}
		}
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(boxList);
        response.setRecords(response.getRecords());
        return response;
	}
	
	/**
	 * 保存样本
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity saveQn(HttpServletRequest request){
		Map<String,Object> res = new HashMap<String, Object>();
		try{
			scmlService.save(request);
		}catch (Exception e) {
			e.printStackTrace();
			res.put("success", false);
			res.put("msg", "保存失败");
			return new ResponseEntity(res, HttpStatus.OK);
		}
		res.put("success", true);
		res.put("msg", "保存成功");
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@RequestMapping("checktubecode")
	public @ResponseBody ResponseEntity checkTubeCode(@RequestParam("tubecode")String tubeCode ,
													  @RequestParam("boxcode") String boxcode){
		Map<String,Object> res = new HashMap<String, Object>();
        res.put("canuse",scmlService.checkTubeCode(tubeCode,boxcode));
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
}
