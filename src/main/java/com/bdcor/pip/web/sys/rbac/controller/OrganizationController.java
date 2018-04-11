package com.bdcor.pip.web.sys.rbac.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.bdcor.pip.core.beanvalidator.BeanValidators;
import com.bdcor.pip.web.sys.rbac.domain.OrganType;
import com.bdcor.pip.web.sys.rbac.domain.Organization;
import com.bdcor.pip.web.sys.rbac.service.OrganizationService;
 
/**
 * <pre>
 * 功能说明：组织机构管理Controller
 * </pre>
 * 
 * @author <a href="mailto:shao.gq@gener-tech.com">ShaoGuoqing</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/sys/rbac/organization")
public class OrganizationController{
    @Autowired
    private OrganizationService organizationService;
    
    @RequiresPermissions("/sys/rbac/organization")
    @RequestMapping
    public ModelAndView init() {
        
        ModelAndView mav = new ModelAndView("sys/rbac/organization/list");
        List<OrganType> orgTypeList = organizationService.getOrganType();
        mav.addObject("orgTypeList", orgTypeList);
        return mav;
    }

    /**
     * 查询组织列表
     * @param filter
     * @return
     */
    @RequiresPermissions("/sys/rbac/organization")
    @RequestMapping(value = "list")
    @ResponseBody
    public List<Organization> list(String type,String root) {
    	//root = "59e7e6a4d0f542adb256cf4e74bd89e9";
    	Map condtion = new HashMap();
    	if(!StringUtils.isBlank(type) ){
	    	condtion.put("type", type);  // 要获取的组织类型
	    }
    	if(!StringUtils.isBlank(root) ){
    		condtion.put("root", root);  // 根节点
	    }
    	List<Organization> organizationList = organizationService.list(condtion);
        return organizationList;
    }
    
    /**
     * 新增组织架构
     */
    @RequiresPermissions("orgmgt:add")
    @RequestMapping(value = "save",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(Organization organization) {
        // 调用JSR303 Bean Validator进行校验, 异常将由ConstraintViolationExceptionHandler统一处理.
        BeanValidators.validateWithException(organization);
        organizationService.save(organization);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    /**
     * 修改组织架构
     */
    @RequiresPermissions("orgmgt:update")
    @RequestMapping(value = "update",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(Organization organization) {
        // 调用JSR303 Bean Validator进行校验, 异常将由ConstraintViolationExceptionHandler统一处理.
        BeanValidators.validateWithException(organization);
        organizationService.update(organization);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    /**
     * 删除组织架构
     */
    @RequiresPermissions("orgmgt:delete")
    @RequestMapping(value = "delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam(value = "id") List<String> idList) {
        
        organizationService.delete(idList);
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("success", true);
        
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    /**
     * 组织机构重命名
     * @param id
     * @param newName
     * @return
     */
    @RequiresPermissions("orgmgt:update")
    @RequestMapping(value="rename",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> rename(@RequestParam(value="id",required=true)String id,@RequestParam(value="newName",required=true)String newName){
        
        organizationService.rename(id,newName);
        
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    @RequestMapping(value="/checkExists")
    public @ResponseBody Boolean checkExists(String name,@RequestParam(value="id",required=false)String id){
    	
    	return organizationService.checkExists(name,id);
    }
}
