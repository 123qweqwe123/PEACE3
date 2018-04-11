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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.domain.LinkMan;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;
import com.bdcor.pip.web.pro.promgt.filter.LinkManFilter;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.LinkManService;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;

@Controller
@RequestMapping("pro/linkman")
public class LinkManController {

	@Autowired
	private LinkManService linkManService;
	@Autowired
	private LccService lccService;
	@Autowired
	private ProjectMgtService projectMgtService;
	
	@RequestMapping
	public String init(Model model){
		model.addAttribute("lccDictList", lccService.getAllLccs(new LccFilter()));
		return "pro/linkman/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<LinkMan> getAllMaterInfos(LinkManFilter filter){
		List<LinkMan> list = linkManService.getAllLinkMans(filter);
		JqgridResponse<LinkMan> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
    @RequestMapping(value = "openmodaladdlinkmaninput", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "linkManCode", required = false) String linkManCode) {
    	Project pro = projectMgtService.getProjectById(Securitys.getUser().getCurrent_projectId());
    	ModelAndView mav = new ModelAndView("pro/linkman/form");
    	LinkMan linkMan = new LinkMan();
    	if(linkManCode!=null&&!"".equals(linkManCode)){
    		linkMan = linkManService.getLinkManByCode(linkManCode);
    	}
    	if(linkMan==null) linkMan = new LinkMan();
    	linkMan.setProjectName(pro==null?"":pro.getProjectName());
    	mav.addObject("linkMan", linkMan);
    	mav.addObject("lccDictList", lccService.getAllActiveLcc());
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addLinkMan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addLinkMan(LinkMan linkMan){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(linkMan.getLinkManCode())){
				linkManService.addLinkMan(linkMan);
			}else{
				linkManService.updateLinkMan(linkMan);
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
	public ResponseEntity<?> delete(String linkManCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			linkManService.delete(linkManCode);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@RequestMapping("view")
	public String view(Model model){
		model.addAttribute("lccDictList", lccService.getAllLccs(new LccFilter()));
		return "pro/linkman/view";
	}
	
	
}
