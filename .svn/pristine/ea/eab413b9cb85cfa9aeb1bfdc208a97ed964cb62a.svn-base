package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.material.supp.domain.MaterlInfo;
import com.bdcor.pip.web.material.supp.filter.MaterlInfoFilter;
import com.bdcor.pip.web.material.supp.service.MaterlInfoService;

@Controller
@RequestMapping("material/info")
public class MaterlInfoController {

	@Autowired
	private MaterlInfoService materlInfoService;
	
	@RequestMapping
	public String init(){
		return "material/info/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterlInfo> getAllMaterInfos(MaterlInfoFilter filter){
		List<MaterlInfo> list = materlInfoService.getAllMaterInfos(filter);
		JqgridResponse<MaterlInfo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
    @RequestMapping(value = "openmodaladdmaterinfoinput", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "materlInfoCode", required = false) String materlInfoCode) {
    	ModelAndView mav = new ModelAndView("material/info/form");
    	if(materlInfoCode!=null&&!"".equals(materlInfoCode)){
    		MaterlInfo materlInfo = materlInfoService.getMaterlInfoByCode(materlInfoCode);
    		mav.addObject("materlInfo", materlInfo);
    	}
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addMaterlInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addMaterlInfo(MaterlInfo materlInfo){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(materlInfo.getMaterlInfoCode())){
				materlInfoService.addMaterInfo(materlInfo);
			}else{
				materlInfoService.updateMaterInfo(materlInfo);
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(String materlInfoCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materlInfoService.delete(materlInfoCode);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
    
}
