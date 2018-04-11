/**
 * ModuleController.java Create on 2013年7月2日
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.sys.rbac.domain.Module;
import com.bdcor.pip.web.sys.rbac.domain.Permission;
import com.bdcor.pip.web.sys.rbac.filter.ModuleFilter;
import com.bdcor.pip.web.sys.rbac.service.ModuleService;
import com.bdcor.pip.web.sys.rbac.service.PermissionService;



/**
 * <pre>
 * 功能说明：模块管理Controller
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/rbac/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    
    @Autowired
    private PermissionService permissionService;

    /**
     * 初始化列表
     * 
     * @return
     */
    @RequiresPermissions("sysmgt:module")
    @RequestMapping
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView("sys/rbac/module/list");

        return mav;
    }

    /**
     * 列表JSON数据
     * 
     * @param filter
     * @return
     */
    @RequiresPermissions("sysmgt:module")
    @RequestMapping(value = "getAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JqgridResponse<Module> getList(ModuleFilter filter) {
        List<Module> all = moduleService.getByFilter(filter);
        JqgridResponse<Module> response = JqgridResponseContext
                .getJqgridResponse();
        response.setRows(all);
        return response;
    }

    /***
     * 初始化编辑页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value={"modulemgt:add","modulemgt:update"},logical=Logical.OR)
    @RequestMapping(value = "openmodalmoduleinput", method = RequestMethod.GET)
    public ModelAndView ModuleInput(
            @RequestParam(value = "id", required = false) String id) {
        
        ModelAndView mav = new ModelAndView("sys/rbac/module/form");
        
        if (!StringUtils.isBlank(id)) {
            
            Module module = moduleService.getById(id);
            List<Permission> ownPermissions = permissionService.getPermissionsByModuleId(id);
            mav.addObject("ownPermissions", ownPermissions);
            mav.addObject("module", module);
        }

        return mav;
    }
    
    @RequiresPermissions(value={"modulemgt:add","modulemgt:update"},logical=Logical.OR)
    @RequestMapping(value="getAllPermissions",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Permission> getAllPermissions(){
    	List<Permission> permissions = permissionService.getAllModulePermissions();
    	return permissions;
    }

    /**
     * 批量删除
     * 
     * @param id
     * @return
     */
    @RequiresPermissions("modulemgt:delete")
    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Boolean delete(@RequestParam(value = "ids", required = true) String ids) {
        String[] idsArr = ids.split(",");
        /*List<String> idS = new ArrayList<String>();
        for (String id : idsArr) {
            idS.add(id);
        }*/
        moduleService.deleteByIds(idsArr);

        return true;
    }

    /**
     * 更新
     * 
     * @param module
     * @return
     */
    @RequiresPermissions("modulemgt:update")
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Boolean update(Module module,@RequestParam(value="pIds",required=false)String pIds) {
    	
    	if(StringUtils.isNotBlank(pIds)){
            
            String[] ids = pIds.split(",");
            
            moduleService.update(module,ids);
        }else{
            moduleService.update(module,null);
        }
        return true;
    }

    /**
     * 保存
     * 
     * @param module
     * @return
     */
    @RequiresPermissions("modulemgt:add")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Boolean save(Module module,@RequestParam(value="pIds",required=false)String pIds) {
    	
    	if(StringUtils.isNotBlank(pIds)){
            
            String[] ids = pIds.split(",");
            
            moduleService.save(module,ids);
        }else{
            moduleService.save(module);
        }
        return true;
    }

    /**
     * 检查模块名称是否存在
     * @param name
     * @param isUpdate
     * @return
     */
    @RequestMapping(value="/checkExists")
    public @ResponseBody boolean checkExists(String name,@RequestParam(value="id",required=false)String id){
    	return moduleService.checkExists(name, id);
    }
}
