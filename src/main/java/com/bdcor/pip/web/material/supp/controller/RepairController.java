package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.material.supp.domain.Repair;
import com.bdcor.pip.web.material.supp.filter.RepairFilter;
import com.bdcor.pip.web.material.supp.service.RepairService;

@Controller
@RequestMapping("material/repair")
public class RepairController {

	@Autowired
	private RepairService repairService;
	
	@RequestMapping
	public String init(){
		return "material/repair/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Repair> getAllRepairs(RepairFilter filter){
		List<Repair> list = repairService.getAllRepairs(filter);
		JqgridResponse<Repair> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
    @RequestMapping(value = "openmodaladdrepairinput", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "id", required = false) String id) {
    	ModelAndView mav = new ModelAndView("material/repair/form");
    	if(id!=null&&!"".equals(id)){
    		Repair repair = repairService.getRepairByCode(id);
    		mav.addObject("repair", repair);
    	}
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addRepair", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addRepair(Repair repair){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(repair.getProjectId())){
				repairService.addRepair(repair);
			}else{
				repairService.updateRepair(repair);
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
	public ResponseEntity<?> delete(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			repairService.delete(id);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
    
}
