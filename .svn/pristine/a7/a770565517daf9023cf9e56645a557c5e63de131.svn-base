package com.bdcor.pip.web.fee.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.fee.domain.FeePlanVo;
import com.bdcor.pip.web.fee.service.FeePlanService;
import com.bdcor.pip.web.spem.domain.IceBoxRegVo;

@Controller
@RequestMapping("fee/plan") 
public class FeePlanController {
	
	
	@Autowired
	private FeePlanService feePlanService;
	
	
	
	@RequestMapping(value="feePlan_init",method = RequestMethod.GET)
	public  String   feePlan(Model model){
		
		
		return "fee/feePlan";  
	} 
	
	
	
	@RequestMapping(value="getFeePlanList",produces="application/json")
	public  @ResponseBody List   planList(String organId){
		
		List<FeePlanVo> data = feePlanService.list(organId);
		
		return data;   
	}     
	
	
	@RequestMapping(value="openmodal_feePlanForm",method = RequestMethod.GET)
	public  ModelAndView   feePlanForm(String id){ 
		
		 ModelAndView mav = new ModelAndView("fee/feePlanForm"); 
		 FeePlanVo fpv = null;
	       if (!StringUtils.isBlank(id)) {
	    	   fpv = feePlanService.getById(id);   
	        }else{
	        	fpv = new FeePlanVo();
	        	//fpv.setProjectid(Securitys.getUser().getCurrent_projectName());
	        	//fpv.setLccid(Securitys.getUser().getLccName());
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	        	fpv.setProjectId(Securitys.getUser().getCurrent_projectName());
	        	fpv.setOrganId(Securitys.getUser().getLccName());
	        	fpv.setAmount(0);
	        	fpv.setActivat_timeString(dateFormat.format(new Date(System.currentTimeMillis())) );
	        	Calendar cal = Calendar.getInstance();
	        	cal.setTime(new Date());
	        	cal.add(Calendar.MONTH, 3);
	        	fpv.setPlan_complete_timeString(dateFormat.format(cal.getTime()));
		   }     
	       mav.addObject("pv", fpv); 
		   return mav;
	} 
	 
@SuppressWarnings("unused")
@RequestMapping(value = "save" ,produces=MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<?>  save(FeePlanVo vo){  
		
		int i = feePlanService.save(vo);
				
		
			
		Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("success", true);
        
        return new ResponseEntity(res, HttpStatus.OK);
}


@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody Boolean delete(@RequestParam(value="id",required=true)String id){
	try{
		feePlanService.delete(id);
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
