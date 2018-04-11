package com.bdcor.pip.web.fee.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
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
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.fee.domain.FeeCategoryStatisVo;
import com.bdcor.pip.web.fee.domain.FeeDepartStatisVo;
import com.bdcor.pip.web.fee.domain.FeePlanVo;
import com.bdcor.pip.web.fee.domain.FeeRegister;
import com.bdcor.pip.web.fee.domain.NCDepartVo;
import com.bdcor.pip.web.fee.domain.PipActualRegExample;
import com.bdcor.pip.web.fee.domain.RegTypeVo;
import com.bdcor.pip.web.fee.filter.FeeFilter;
import com.bdcor.pip.web.fee.filter.FeeRegFilter;
import com.bdcor.pip.web.fee.service.FeeManagerService;  
import com.bdcor.pip.web.fee.service.NCDepartVoService;
import com.bdcor.pip.web.fee.service.RegTypeVoService;

@SuppressWarnings("restriction")
@Controller
@RequestMapping("fee") 
public class FeeManagerController { 
	
	@Autowired
	private FeeManagerService feeManagerService;
	
	@Autowired
	private NCDepartVoService ncdepartVoService; 
	
	@Autowired
	private  RegTypeVoService  regTypeVoService; 
	
	@RequestMapping(value="use_query",method = RequestMethod.GET)
	public  String   use(Model model){
		
		return "fee/list";
	}    
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="getFeeList",produces="application/json")
	public  @ResponseBody Map   useData(FeeFilter filter, HttpServletRequest req){
		
		Map data = getFeeManagerService().getFeeList(filter);
		
		return data;  
	}    
	
	
	@RequestMapping(value="openmodalSelectOrgan",method = RequestMethod.GET )
	public  String   openForm(){
		return "fee/selectOrganform";
	}    
	
	
	@RequestMapping(value="feeReg_init",method = RequestMethod.GET)
	public  ModelAndView   feeReg(Model model){
		
		ModelAndView mav = new ModelAndView("fee/feeReg");
		
		List<NCDepartVo>  list = ncdepartVoService.getAllNCDepart(); 
		
		mav.addObject("dptList", list);
		return mav; 
	}
	
	

	@RequestMapping(value="getFeeRegList",produces="application/json")
	public  @ResponseBody JqgridResponse   regList(FeeRegFilter filter){
		
		List<FeeRegister> data = getFeeManagerService().getFeeRegList(filter);
		JqgridResponse<FeeRegister> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		
		return response; 
		
	}    
	
	
	@RequestMapping(value="feeStatis_init",method = RequestMethod.GET)
	public  String   feeStatis(Model model){
		
		
		return "fee/feeStatistics"; 
	}
	
	
	
	
	
	@RequestMapping(value="getFeeStatisticsByCategory",produces="application/json")
	public  @ResponseBody Map   FeeStatisticsByCategory(String dateTo){
		String proId = Securitys.getCurrentProject(); 
		Map data = getFeeManagerService().getFeeStatisticsByCategory(dateTo); 
		return data;  
	}     
	
	
	@RequestMapping(value="getFeeStatisticsByDepart",produces="application/json")
	public  @ResponseBody Map   FeeStatisticsByDepart(String dateTo){
		String proId = Securitys.getCurrentProject();
		Map data = getFeeManagerService().getFeeStatisticsByDepart(dateTo);
		return data;   
	} 
	
	
	
	@RequestMapping(value="openmodal_feeRegForm",method = RequestMethod.GET)
	public  ModelAndView   FeeRegForm(String id){
		
		ModelAndView mav = new ModelAndView("fee/feeRegForm");
		
		List<NCDepartVo>  list = ncdepartVoService.getAllNCDepart();
		
		List<RegTypeVo>   data =  regTypeVoService.getAll(); 
		
		FeeRegister fr = null; 
		
        mav.addObject("ocx", list);
        
        mav.addObject("regtype", data);
		
		 if (!StringUtils.isBlank(id)) {
			 fr =  getFeeManagerService().getById(id);
			 mav.addObject("vo", fr);
				
			return mav;   
		 }
		 
		 fr = new FeeRegister();
		 
		 fr.setProjectName(Securitys.getUser().getCurrent_projectName()); 
		 fr.setReg_date(new Date());
		 mav.addObject("vo", fr);
		
		return mav;   
	} 
	
	
	
	@RequestMapping(value = "save" ,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  save(FeeRegister vo){ 
			
			int i = getFeeManagerService().save(vo);
					
			 
			Map<String, Object> res = new HashMap<String, Object>();
	        
	        res.put("success", true);
	        
	        return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "update" ,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  update(FeeRegister vo){ 
			
			int i = getFeeManagerService().update(vo);
					
			 
			Map<String, Object> res = new HashMap<String, Object>();
	        
	        res.put("success", true);  
	        
	        return new ResponseEntity(res, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity delete(@RequestParam(value="id",required=true)String id){
		
			int i= getFeeManagerService().delete(id);
			
			Map<String, Object> res = new HashMap<String, Object>();
			if(i>0){
	           res.put("success", true);
			} else{
				res.put("success", false);
				res.put("msg", "没有更新!"); 
			}
	        
	        return new ResponseEntity(res, HttpStatus.OK);
	}   


	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
	}     
	
	
	
	
	
	
	
	public FeeManagerService getFeeManagerService() {
		return feeManagerService;
	}
	
	public void setFeeManagerService(FeeManagerService feeManagerService) {
		this.feeManagerService = feeManagerService;
	}	
}
