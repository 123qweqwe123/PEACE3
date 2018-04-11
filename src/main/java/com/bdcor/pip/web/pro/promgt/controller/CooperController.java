package com.bdcor.pip.web.pro.promgt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.pro.promgt.domain.Region;
import com.bdcor.pip.web.pro.promgt.filter.RegionFilter;
import com.bdcor.pip.web.pro.promgt.service.CooperService;
import com.bdcor.pip.web.sys.rbac.domain.Organization;
import com.bdcor.pip.web.sys.rbac.service.OrganizationService;

@Controller
@RequestMapping(value = "pro/region")
public class CooperController {
	
	@Autowired
	private CooperService cooperService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping
    public String init(){
        return "pro/region/list";
    }
	
	@RequestMapping(value = "listRegion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Region> listRegion(RegionFilter filter){
		List<Region> regionList = cooperService.listRegion(filter);
		JqgridResponse<Region> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(regionList);
        return response;
	}

    @RequestMapping(value = "openmodalLccinput", method = RequestMethod.GET)
    public String regionInput(Model model, String regionId) {
    	Region region = cooperService.getRegionById(regionId==null?"":regionId);
		model.addAttribute("region", region);
    	return "pro/region/lccform";
    }
    
	@RequestMapping(value = "listOrg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Organization> listOrg() {
		return organizationService.getOrgTree(new Integer[]{1,2,3});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "saveRegion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> saveRegion(Region region){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(region.getRegionId())){
				cooperService.saveRegion(region);
			}else{
				cooperService.updateRegion(region);
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "deleteLcc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteLcc(String regionId){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			cooperService.deleteLcc(regionId);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
}
