package com.bdcor.pip.web.pro.promgt.controller;

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
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.ProjectFilter;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;


@Controller
@RequestMapping(value = "pro/promgt")
public class ProjectMgtController {
	
	@Autowired
	private ProjectMgtService projectMgtService;

	@RequestMapping
    public String init(){
        return "pro/promgt/list";
    }
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Project> getAllProjects(ProjectFilter filter){
		List<Project> pList = projectMgtService.getAllProjects(filter);
		JqgridResponse<Project> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(pList);
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "addProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addProject(Project project){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(project.getProjectId())){
				String projectId = projectMgtService.addProject(project);
				result.put("projectId", projectId);
			}else{
				projectMgtService.updateProject(project);
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @RequestMapping(value = "openmodaladdprojectinput", method = RequestMethod.GET)
    public ModelAndView addProjectInput(@RequestParam(value = "projectId", required = false) String projectId) {
    	ModelAndView mav = new ModelAndView("pro/promgt/form");
    	if(projectId!=null&&!"".equals(projectId)){
    		Project project = projectMgtService.getProjectById(projectId);
    		mav.addObject("project", project);
    		mav.addObject("edit", true);
    	}else{
    		mav.addObject("edit", false);
    	}
    	return mav;
    }
    
    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Project> getAllProjects1(ProjectFilter filter){
		return projectMgtService.getAllProjects(filter);
	}
    
    @RequestMapping(value = "openmodalchooseproject", method = RequestMethod.GET)
    public ModelAndView openChoseProjectWindow(){
    	ModelAndView mav = new ModelAndView("pro/promgt/chooseproject");
    	return mav;
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteProject(String projectId){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			projectMgtService.delete(projectId);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "doStartStop", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> doStartStop(String projectId, String type){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			projectMgtService.doStartStop(projectId, type);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="checkNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkNameExists(@RequestParam(value = "projectName") String projectName){
        Boolean result = projectMgtService.checkNameExists(projectName);
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("result", result);
        return new ResponseEntity(res,HttpStatus.OK);
    }
	
}
