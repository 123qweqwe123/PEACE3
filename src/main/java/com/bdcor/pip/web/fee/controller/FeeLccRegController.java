package com.bdcor.pip.web.fee.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.fee.domain.FeePlanVo;
import com.bdcor.pip.web.fee.domain.FeeRegister;
import com.bdcor.pip.web.fee.domain.NCDepartVo;
import com.bdcor.pip.web.fee.domain.PipActualReg;
import com.bdcor.pip.web.fee.filter.FeeLccRegFilter;
import com.bdcor.pip.web.fee.service.FeeLccRegService;
import com.bdcor.pip.web.fee.service.FeePlanService;
import com.bdcor.pip.web.fee.service.NCDepartVoService;
import com.bdcor.pip.web.sys.rbac.domain.Organization;
import com.bdcor.pip.web.sys.rbac.domain.User;

@Controller
@RequestMapping("fee/LccReg") 
public class FeeLccRegController { 
	
	
	@Autowired
	private FeeLccRegService feeLccRegService;
	
	
	
	
	
	@RequestMapping(value="feeLccReg_init",method = RequestMethod.GET)
	public  String   feePlan(Model model){
		
		
		return "fee/feeLccReg";   
	}       
	
	
	
	@RequestMapping(value="getLccRegList",method = RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody JqgridResponse   planList(FeeLccRegFilter fi){  
		
		List<PipActualReg> data = feeLccRegService.list(fi);
		JqgridResponse<PipActualReg> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		
		return response;  
	}           
	
	
	@RequestMapping(value="openmodal_feePlanForm",method = RequestMethod.GET)
	public  ModelAndView   feePlanForm(Model model,String id){ 
		
		  PipActualReg ar=null;
		  ModelAndView mav = new ModelAndView("fee/feeLccRegForm");
	        if (!StringUtils.isBlank(id)) {
	            
	        	ar = feeLccRegService.getById(id);  
	        	mav.addObject("ar", ar); 
	       	  return mav;
	       }  
	        ar = new  PipActualReg();
	        ar.setCostDate(new Date());
	        mav.addObject("ar", ar); 
	     return mav;
    }   
	 
@SuppressWarnings("unused")
@RequestMapping(value = "save" ,method = RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<Map>  save(PipActualReg vo){  
	
	    int i = feeLccRegService.save(vo);
		
		Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("success", true);
        
        return new ResponseEntity(res, HttpStatus.OK);
}


@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody Boolean delete(@RequestParam(value="id",required=true)String id){
	try{
		feeLccRegService.delete(id);
		return true;
	}catch(Exception e){ 
		return false;
	} 
}   


@InitBinder    
public void initBinder(WebDataBinder binder) {    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
    dateFormat.setLenient(false);    
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
}   
	

}
