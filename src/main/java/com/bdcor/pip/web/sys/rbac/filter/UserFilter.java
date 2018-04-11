package com.bdcor.pip.web.sys.rbac.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:shao.gq@gener-tech.com">ShaoGuoqing</a>
 * @version 1.0
 * @author yangfeng
 * @version 2.0
 */

public class UserFilter extends BaseFilter {
	private String loginName;
	private String name;
	private String userId;
	private String projectId;
	private Integer isAdmin;
	// 所属单位查询使用
	private String lccName;

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getLccName() {
		return lccName;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
