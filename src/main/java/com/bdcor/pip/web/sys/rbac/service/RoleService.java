package com.bdcor.pip.web.sys.rbac.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.bdcor.pip.web.sys.rbac.domain.Role;
import com.bdcor.pip.web.sys.rbac.filter.RoleFilter;

/**
 * 
 * <pre>
 * 功能说明：角色Service interface
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public interface RoleService {

	/**
	 * 获取所有角色
	 * 
	 * @return
	 */
	public List<Role> getAll();

	/**
	 * 获取所有角色
	 * 
	 * @return
	 */
	public List<Role> getAllByOrganId(String organId);

	/**
	 * 按条件获取角色
	 * 
	 * @param filter
	 * @return
	 */
	public List<Role> getRolesByFilter(RoleFilter filter);

	/**
	 * 获取用户所有角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRolesByUserId(String userId);

	/**
	 * 获取用户所有角色
	 * 
	 * @param userId
	 * @param tanentId
	 * @return
	 */
	List<Role> getRolesByUserIdAndOrganId(String userId, String organId);

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	public String save(Role role);

	/**
	 * 添加角色权限
	 * 
	 * @param role
	 * @param permissions
	 * @return
	 */
	public String save(Role role, String[] permissions);

	/**
	 * 根据id获取角色
	 * 
	 * @param roleId
	 * @return
	 */
	public Role getById(String roleId);

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 */
	public String update(Role role);

	/**
	 * 更新角色权限
	 * 
	 * @param role
	 * @param permissions
	 * @return
	 */
	public String update(Role role, String[] permissions);

	/**
	 * 删除角色
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 批量删除角色
	 * 
	 * @param ids
	 */
	public void deleteByIds(String[] ids);

	/**
	 * 查询角色关联用户
	 * 
	 * @param ids
	 * @return
	 */
	Long getUserRoleCount(String[] ids);

	boolean checkExists(String name, String id);

	void deleteByRoleId(String id);

	void addTailRole(String[] ids, String roleId);

	
	
}
