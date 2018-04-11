/**
 * RoleController.java Create on 2013-7-3
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
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.rbac.domain.Permission;
import com.bdcor.pip.web.sys.rbac.domain.Role;
import com.bdcor.pip.web.sys.rbac.filter.RoleFilter;
import com.bdcor.pip.web.sys.rbac.service.PermissionService;
import com.bdcor.pip.web.sys.rbac.service.RoleService;

/**
 * <pre>
 * 功能说明：角色Controller
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/sys/rbac/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PermissionService permissionService;


    /**
     * 初始化
     * 
     * @return
     */
    @RequiresPermissions("/sys/rbac/role")
    @RequestMapping
    public ModelAndView init() {

        ModelAndView mav = new ModelAndView("sys/rbac/role/list");

        return mav;
    }

    /**
     * 列表  
     * @return  JSON数据
     */
    @RequiresPermissions("/sys/rbac/role")
    @RequestMapping(value = "getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JqgridResponse<Role> getAll(RoleFilter filter) {
    	if(!"99".equals(Securitys.getUser().getLccCode())){
    		filter.setDeptLevel(1);
    	}
        List<Role> list = roleService.getRolesByFilter(filter);
        
        JqgridResponse<Role> response = JqgridResponseContext.getJqgridResponse();

        response.setRows(list);
        
        return response;
    }
    
    /***
     * 初始化编辑页面
     * 
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value={"rolemgt:add","rolemgt:update"},logical = Logical.OR)
    @RequestMapping(value = "openmodalroleinput", method = RequestMethod.GET)
    public ModelAndView roleInput(@RequestParam(value = "id", required = false) String id) {
        ModelAndView mav = new ModelAndView("sys/rbac/role/form");
        
        if (!StringUtils.isBlank(id)) { 
            
        	Role role = roleService.getById(id);
            
        	mav.addObject("role", role);
        	
        	List<Permission> permissions = permissionService.getPermissionsByRoleId(id);
        	
        	mav.addObject("ownPermissions", permissions);
        }
        return mav;
    }
    
    /**
     * 该租户下所有权限数据
     * @return
     */
    @RequestMapping(value = "getAllPermission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Permission> getPermissions(){
        
        //return permissionService.getByOrganId(Securitys.getOrganId());
    	return permissionService.getPermissionByOrganId(Securitys.getOrganId());
    
    }
    
    
    /**
     * 添加角色权限
     * @param role
     * @param pIds
     * @return
     */
    @RequiresPermissions("rolemgt:add")
    @RequestMapping(value="save",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Boolean save(Role role,@RequestParam(value="pIds",required=false)String pIds){
        
        if(StringUtils.isNotBlank(pIds)){
            pIds = pIds + ",lookatmenu,basicchangepassword";
            String[] ids = pIds.split(",");
            
            roleService.save(role,ids);
        }else{
            
            roleService.save(role);
        }
        return true;
    }
    
    /**
     * 保存角色权限
     * @param role
     * @param pIds
     * @return
     */
    @RequiresPermissions("rolemgt:update")
    @RequestMapping(value="update",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Boolean update(Role role,@RequestParam(value="pIds",required=false)String pIds){
        
        if(StringUtils.isNotBlank(pIds)){
        	pIds = pIds + ",lookatmenu,basicchangepassword";
            String[] ids = pIds.split(",");
            
            roleService.update(role,ids);
        }else{
            
            roleService.update(role,null);
        }
        return true;
    }
    
    /**
     * 删除角色
     * @param ids
     * @return
     */
    @RequiresPermissions("rolemgt:delete")
    @RequestMapping(value="delete",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Boolean delete(@RequestParam(value="ids",required=true)String ids){
        
        String[] sp = ids.split(",");
        
        roleService.deleteByIds(sp);
        
        return true;
    }
    
    /**
     * 查找关联用户数量
     * @param ids
     * @return
     */
    @RequestMapping(value="getUserRoleCount",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long getUserRoleCount(@RequestParam(value="ids",required=true)String ids){
    	 String[] sp = ids.split(",");
    	return roleService.getUserRoleCount(sp);
    }
    
    @RequestMapping(value="/checkExists")
    public @ResponseBody Boolean checkExists(String name,@RequestParam(value="id",required=false)String id){
    	return roleService.checkExists(name, id);
    }
    
    
    
  //关联机尾----->添加和移除
  	@RequestMapping(value = "saveRoleTail", method=RequestMethod.POST,  produces = "application/json")
  	public @ResponseBody boolean saveFleetTail(@RequestParam(value = "tailIds") String ids, @RequestParam(value = "roleId") String roleId){
  		//全部移除
  		if(ids == ""){
  			roleService.deleteByRoleId(roleId);
  			return true;
  		}
  		//移除,添加
  		String[] idsArray = ids.split(",");		
  		roleService.deleteByRoleId(roleId);			
  		roleService.addTailRole(idsArray, roleId);
  		return true;
  	}
  	
  
  	
  	
}
