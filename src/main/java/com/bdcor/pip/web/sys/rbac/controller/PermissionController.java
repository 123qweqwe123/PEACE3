/**
 * PermissionController.java Create on 2013-6-25
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.rbac.domain.Permission;
import com.bdcor.pip.web.sys.rbac.service.PermissionService;

/**
 * <pre>
 * 功能说明：权限管理Controller
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value = "sys/rbac/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("permissionmgt:administrator")
	@RequestMapping
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sys/rbac/permission/list");

		return mav;
	}

	@RequiresPermissions("/sys/rbac/permission")
	@RequestMapping(value = "getAllPermission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<Permission> getAllPermissions() {
		//List<Permission> listPermission = permissionService.getAllPermissions();
		List<Permission> listPermission = permissionService.getAllPermissionsWithOutBase();
		return listPermission;
	}

	@RequestMapping(value = "getByUserIdCatHelp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<Permission> getByUserIdCatHelp() {
		String userId = Securitys.getUserId();
		List<Permission> listPermission = permissionService.queryByUserIdCatHelp(userId);
		return listPermission;
	}
	
	@RequiresPermissions("permissionmgt:add")
	@RequestMapping(value="add",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addPermission(@Valid Permission permission,Errors errors){
		if(errors.hasErrors()){
			return false;
		}
		try{
			permissionService.save(permission);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@RequiresPermissions("permissionmgt:update")
	@RequestMapping(value="rename",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean rename(@RequestParam(value="id",required=true)String id,@RequestParam(value="newName",required=true)String newName){
		try{
			permissionService.rename(id, newName);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@RequiresPermissions("permissionmgt:delete")
	@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delete(@RequestParam(value="id",required=true)String id){
		try{
			permissionService.delete(id);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@RequiresPermissions("permissionmgt:update")
	@RequestMapping(value="update",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean update(@Valid Permission permission,Errors errors){
		if(errors.hasErrors()){
			return false;
		}
		try{
			permissionService.update(permission);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@RequiresPermissions("/sys/rbac/permission")
	@RequestMapping(value = "getAllPermissionForMenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Permission> getAllPermissionsForMenu() {
		List<Permission> listPermission = permissionService.getAllPermissionsForMenu();
		return listPermission;
	}
	
	@RequestMapping(value="checkNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean checkNameExists(Permission permission){
		Permission perm = permissionService.checkNameExists(permission);
		if(perm==null){
			return false;
		}else{
			return true;
		}
	}
}
