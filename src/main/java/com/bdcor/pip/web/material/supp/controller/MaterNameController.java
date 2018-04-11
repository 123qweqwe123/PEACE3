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
import com.bdcor.pip.web.material.supp.domain.MaterName;
import com.bdcor.pip.web.material.supp.filter.MaterNameFilter;
import com.bdcor.pip.web.material.supp.service.MaterNameService;

@Controller
@RequestMapping("material/name")
public class MaterNameController {

	@Autowired
	private MaterNameService materNameService;
	
	@RequestMapping
	public String init(){
		return "material/name/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterName> getAllMaterNames(MaterNameFilter filter){
		List<MaterName> list = materNameService.getAllMaterNames(filter);
		JqgridResponse<MaterName> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
    @RequestMapping(value = "openmodaladdmaternameinput", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "materlCode", required = false) String materlCode) {
    	ModelAndView mav = new ModelAndView("material/name/form");
    	if(materlCode!=null&&!"".equals(materlCode)){
    		MaterName materName = materNameService.getMaterlNameByCode(materlCode);
    		mav.addObject("materName", materName);
    	}
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addMaterlName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addMaterlName(MaterName materName){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(materName.getMaterlCode())){
				materNameService.addMaterNameService(materName);
			}else{
				materNameService.updateMaterNameService(materName);
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
	public ResponseEntity<?> delete(String materlCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materNameService.delete(materlCode);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="checkNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkNameExists(@RequestParam(value="materlName",required=true) String materlName){
        Boolean result = materNameService.checkNameExists(materlName);
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("result", result);
        return new ResponseEntity(res,HttpStatus.OK);
    }
}
