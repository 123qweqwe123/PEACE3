/**
 * PortalController.java Create on 2013-6-28
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.ProjectFilter;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;
import com.bdcor.pip.web.sys.rbac.domain.Permission;
import com.bdcor.pip.web.sys.rbac.service.MenuService;
import com.bdcor.pip.web.sys.rbac.service.PermissionService;

/**
 * <pre>
 * 功能说明：工作台
 * </pre>
 * 
 * @author <a href="mailto:cui.j@gener-tech.com">John</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/portal")
public class PortalController {

	@Resource
	private PermissionService permissionService;
	@Resource
	private MenuService menuService;
	@Resource
	private ProjectMgtService projectMgtService;

	@RequestMapping(method = RequestMethod.GET)
	public String portal(Model model) {

		String projectId = Securitys.getUser().getCurrent_projectId();
		ProjectFilter filter = new ProjectFilter();
		filter.setSidx("PROJECTNAME");
		filter.setSord("DESC");
		List<Project> projectList = projectMgtService.getAllProjects(filter);
		model.addAttribute("projectId", projectId);
		if(projectList.size()==1){
			String proId = projectList.get(0).getProjectId();
			model.addAttribute("projectId", proId);
			Securitys.getUser().setCurrent_projectId(proId);
		}
		return "portal";			
		
	}
	
	
	
	

	/**
	 * portal页面跳转
	 * 
	 * @param target
	 * @return
	 */
	@RequestMapping(value = "redirectTo", method = RequestMethod.GET)
	public String redirectTo(
			@RequestParam(value = "target", required = true) String target) {
		Permission permission = null;
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			permission = permissionService.getByCodeAndOrganId(target, Securitys.getOrganId());
		} else {
			permission = permissionService.getByCode(target, Securitys.getUserId());
		}
		if (null == permission) {
			throw new UnauthorizedException("没有权限访问该资源");
		}

		// 目标url//
		String targetUrl = permission.getUrl();
		if (StringUtils.isBlank(targetUrl) || targetUrl.equals("/")
				|| targetUrl.equals("\\")) {
			// 继续深入,直到找到第一个URL不为空的菜单//
			targetUrl = getRedirectURL(permission.getId());
		}
		if (StringUtils.isBlank(targetUrl)) {
			throw new ServiceException("资源不存在");
		}
		return "redirect:" + targetUrl;
	}

	public String getRedirectURL(String pId) {
		Permission pm = null;
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			pm = permissionService.getByParentIdAndOrganId(pId,Securitys.getOrganId());
		} else {
			pm = permissionService.getByParentId(pId, Securitys.getUserId());
		}
		if (pm == null) {
			throw new UnauthenticatedException("未知路径");
		}

		String url = pm.getUrl();
		if (StringUtils.isBlank(url) || url.equals("/") || url.equals("\\")) {
			// 继续深入,直到找到第一个URL不为空的菜单//
			return getRedirectURL(pm.getId());
		} else {
			return url;
		}
	}
}
