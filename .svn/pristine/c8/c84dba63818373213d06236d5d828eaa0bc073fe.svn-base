/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.bdcor.pip.web.sys.rbac.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.security.shiro.ShiroUser;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;
import com.bdcor.pip.web.sys.logmgt.service.LogService;
import com.bdcor.pip.web.sys.rbac.domain.Permission;
import com.bdcor.pip.web.sys.rbac.domain.Role;
import com.bdcor.pip.web.sys.rbac.domain.User;
import com.bdcor.pip.web.sys.rbac.service.CustomFunctionService;
import com.bdcor.pip.web.sys.rbac.service.PermissionService;
import com.bdcor.pip.web.sys.rbac.service.RoleService;
import com.bdcor.pip.web.sys.rbac.service.UserService;



/**
 * 
 * <pre>
 * 功能说明：安全认证类，登陆，获取权限角色
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public class ShiroDbRealm extends AuthorizingRealm {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected UserService userService;
	@Autowired
	protected PermissionService permissionService;
	@Autowired
	protected RoleService roleService;
	@Autowired
	private LogService logService;
	@Autowired
	private ProjectMgtService projectMgtService;
	
	@Autowired
	private LccService lccService;
	

	@Autowired
	private CustomFunctionService customFunctionService; 

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		try {
			MyAuthenticationToken token = (MyAuthenticationToken) authcToken;
			User user = userService.login(token.getUsername(),
					new String(token.getPassword()));
			Integer flg = user.getIsAdmin(); 
			Boolean isAdmin = false;
			Boolean isOrganAdmin = false;
			if (null != flg) {
				if (flg.equals(1)) {
					isAdmin = true;
				} else if (flg.equals(2)) {
					isOrganAdmin = true;
				}
			}
			List<Role> roleList = null;
			List<String> roleIdList = null;
			if (isAdmin) {
				roleList = roleService.getAll();
			} else if (isOrganAdmin) {
				//roleList = roleService.getAllByOrganId(user.getOrganizationId());
				roleList = roleService.getAllByOrganId(user.getOrganizationId());
			} else {
				roleList = roleService.getRolesByUserId(user.getId());
			}
			if (roleList != null && roleList.size() > 0) {
				roleIdList = new ArrayList<String>();
				for (Role r : roleList) {
					roleIdList.add(r.getId());
				}
			}
			String projectName="获取当前项目名称失败!";
			String lccName="获取当前单位失败!";
			String projectid= null;
			String lccId = null;
			try {
				projectid= user.getProjectId();
				lccId =    user.getLccCode();
				projectName = projectMgtService.getProjectById(projectid).getProjectName();
				lccName = lccService.getLcc(lccId,projectid).getLccName();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			ShiroUser su = new ShiroUser(user.getId(), user.getLoginName(),
					user.getName(), isAdmin, user.getOrganizationId(),
					token.getHost(), isOrganAdmin, roleIdList,projectid,lccId,projectName ,lccName, user.getUserCode()); 
			
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(su,
					token.getPassword(), getName());

			/*// 写入用户定制//
			Map customMap = customFunctionService.findKeyMapByUerId(user
					.getId());
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			session.setAttribute("custom", null);*/

			// 添加登陆日志//
			//logService.log(Consts.SystemModel.SYS, LogType.BUSINESS, LogLevel.INFO, "用户" + user.getLoginName() + "登录",su.getTenantId(), su.getId(), su.getIP(), su.getName());

			return info;
		} catch (AuthenticationException e) {
			//throw new ServiceException("", e);
			throw e;
		}

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		

		List<Role> roleList = null;// 角色集合//
		List<String> pList = new ArrayList<String>();
		List<Permission> listP = null;// 权限集合//

		if (shiroUser.isAdmin()) { 
			// 超级管理员获取所有角色//
			roleList = roleService.getAll();
			// 超级管理员获取所有权限//
			listP = permissionService.getAllPermissions();
		} else if (shiroUser.isOrganAdmin()) {
			roleList = roleService.getAllByOrganId(shiroUser.getOrganId());
			listP = permissionService.getByOrganId(shiroUser.getOrganId());
		} else {
			roleList = roleService.getRolesByUserId(shiroUser.getId());
			listP = permissionService.getByUserId(shiroUser.getId());
		}	
		// 遍历角色//
		for (Role role : roleList) {
			
			info.addRole(role.getName());
		}
		// 遍历权限//
		for (Permission per : listP) {
			if (per != null) {
				if(per.getCode()==null){
					System.out.println(per.getName());
					continue;
				}
				String[] ps = per.getCode().split(";");
				for (int i = 0; i < ps.length; i++) {
					pList.add(ps[i]);
				}
			}
		}
		if (shiroUser.isAdmin()) {
			pList.add("permissionmgt:administrator");
		}
		// 基于Permission的权限信息//
		info.addStringPermissions(pList);
		return info;
	}

}
