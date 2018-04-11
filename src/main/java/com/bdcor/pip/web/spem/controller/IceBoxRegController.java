package com.bdcor.pip.web.spem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.domain.IceBoxRegVo;
import com.bdcor.pip.web.spem.domain.SpBoxVo;
import com.bdcor.pip.web.spem.filter.IceBoxRegFilter;
import com.bdcor.pip.web.spem.filter.SpBoxFilter;
import com.bdcor.pip.web.spem.filter.TubeFilter;
import com.bdcor.pip.web.spem.service.IceBoxRegService;
import com.bdcor.pip.web.spem.service.PlanExcel;
import com.bdcor.pip.web.spem.service.SpBoxService;
import com.bdcor.pip.web.spem.utils.SysCodeToNameUtil;

@Controller
@RequestMapping("spem/iceBoxReg") 
public class IceBoxRegController {
	
	
	@Autowired
	private IceBoxRegService iceBoxRegService; 
	@Autowired
	private SpBoxService   spBoxVoService; 
	
	
	@RequestMapping(value="init",method = RequestMethod.GET)
	public  String   iceBoxReg(Model model){
		
		
		return "spem/iceBoxReg";    
	}  
	
	@RequestMapping(value="query",method = RequestMethod.GET)
	public  String   query(Model model){
		
		
		return "spem/iceBoxQuery";    
	}   
	//登记页面
	@RequestMapping(value="rlist",produces="application/json")
	public  @ResponseBody JqgridResponse   list(String lccid){  
		
		List<IceBoxRegVo> data = iceBoxRegService.list(lccid);  
		JqgridResponse<IceBoxRegVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;  
	}
	/**
	 * 查询页面
	 * @param lccid
	 * @return
	 */
	@RequestMapping(value="qlist",produces="application/json")
	public  @ResponseBody JqgridResponse   qlist(IceBoxRegFilter fi){  
		
		List<IceBoxRegVo> data = iceBoxRegService.qlist(fi);  
		JqgridResponse<IceBoxRegVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;  
	} 

	@RequestMapping(value="openmodal",method = RequestMethod.GET)
	public  ModelAndView   openForm(Model model,String id){ 
		  ModelAndView mav = new ModelAndView("spem/iceboxRegForm"); 
		  IceBoxRegVo ibr = null;
	       if (!StringUtils.isBlank(id)) {
	           ibr = iceBoxRegService.getById(id);
	           
	           ibr.setProjectName(SysCodeToNameUtil.getProjectNameByCode(ibr.getProjectid() ) );
	           
	           ibr.setLcc_name(SysCodeToNameUtil.getLccNameByCode(ibr.getLccid()));
	        }else{ 
		       ibr = new IceBoxRegVo();
		       ibr.setProjectid(Securitys.getCurrentProject() );
		       ibr.setProjectName(Securitys.getUser().getCurrent_projectName());
		       ibr.setLccid(Securitys.getUser().getLccCode());
		       ibr.setLcc_name(Securitys.getUser().getLccName());
		   }  
	       mav.addObject("ibr", ibr); 
		  return mav;
    }     
	@SuppressWarnings("unused")
	@RequestMapping(value = "save" ,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  save(IceBoxRegVo vo){ 
		    int i = iceBoxRegService.save(vo);
			
			Map<String, Object> res = new HashMap<String, Object>();
	        
	        res.put("success", true);
	        
	        return new ResponseEntity(res, HttpStatus.OK);
	} 

	@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity delete(@RequestParam(value="id",required=true)String id, String projectid){
		
			
			int i=iceBoxRegService.delete(id,projectid);

			Map<String, Object> res = new HashMap<String, Object>();
	        
	        res.put("success", true);
	        
	        return new ResponseEntity(res, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="selectBox",method = RequestMethod.GET)
	public  ModelAndView   selectBox(String id,String lcc_code){   
		  ModelAndView mav = new ModelAndView("spem/selectBoxForm");  
		  List<?> data = spBoxVoService.selectByExample(id,lcc_code);  
		  mav.addObject("option", data) ;
		  
		 
		  return mav;   
    }   
	
	
	@RequestMapping(value="microList",produces="application/json")
	public  @ResponseBody JqgridResponse   microList(TubeFilter filter){ 
		
		
		List<Map> data = spBoxVoService.microList(filter);  
		JqgridResponse<Map> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;  
	}
	
	
	@RequestMapping(value="boxQuery",method = RequestMethod.GET)
	public  String   boxQuery(Model model){
		
		
		return "spem/boxQuery";    
	}      
	
	
	@RequestMapping(value="boxList",produces="application/json")
	public  @ResponseBody JqgridResponse   boxQuery(SpBoxFilter  filter ){  
		
		List<SpBoxVo>  data = spBoxVoService.spboxListByfilter(filter) ;  
		for(int i=0;i<data.size();i++){//前台jqgrid会自动将自动将json串中的id给设置为主键
			data.get(i).id=i+"";
		}
		JqgridResponse<SpBoxVo> response = JqgridResponseContext.getJqgridResponse(); 
		response.setRows(data);     
		return response;     
	}  
	
	@RequestMapping(value="fridgeList")
	public  @ResponseBody List<Map<String,String>> fridgeList(@RequestParam("lccId")String lccId){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lccId", lccId);
		//paramMap.put("fridgeNameLike",q);
		paramMap.put("limit",999);
		return iceBoxRegService.getFrigdeList(paramMap);
	}
	
	
	/**
	 * 导出转运计划
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("exportPlan")  
	public ModelAndView exportPlan(@RequestParam String fridgeIDs) {  
	        Map model = new HashMap(); 
	        
	        Map<String,Object> paramMap = new HashMap<String, Object>();
	        if(fridgeIDs != null && fridgeIDs.length()>0){
	        	paramMap.put("fridgeIDIn", fridgeIDs.split(","));
	        	List<Map<String,Object>> list = iceBoxRegService.getExportFrigdeList(paramMap);
	        	if(list != null && list.size()>0){
	        		int i=0;
	        		for(Map m : list){
	        			i++;
	        			m.put("id",i);
	        		}
	        	}
	        	model.put("list",list); 
	        }else{
	        	model.put("list",new ArrayList()); 
	        }
			
	        return new ModelAndView(new PlanExcel(), model);  
	 } 

	  
} 
