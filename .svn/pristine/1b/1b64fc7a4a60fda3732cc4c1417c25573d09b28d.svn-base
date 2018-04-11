package com.bdcor.pip.web.sys.rbac.service;

import java.util.List;

import com.bdcor.pip.web.sys.rbac.domain.Permission;

/**
 * 
 * <pre>
 * 功能说明：权限service API
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public interface PermissionService {

	/**
	 * 根菜单
	 */
	Long MENU_LEVEL_0 = 0l;

	/**
	 * 一级菜单
	 */
	Long MENU_LEVEL_1 = 1l;

	/**
	 * 二级菜单
	 */
	Long MENU_LEVEL_2 = 2l;

	/**
	 * 三级菜单
	 */
	Long MENU_LEVEL_3 = 3l;

	/**
	 * 根据角色id查询权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getPermissionsByRoleId(String roleId);

	/**
	 * 查询全部权限
	 * 
	 * @return
	 */
	List<Permission> getAllPermissions();

	/**
	 * 查询全部权限
	 * 
	 * @return
	 */
	List<Permission> getAllPermissionsForMenu();
	
	/**
	 * 添加权限
	 * 
	 * @param permission
	 */
	void save(Permission permission);

	/**
	 * 更新权限
	 * 
	 * @param permission
	 */
	void update(Permission permission);

	/**
	 * 权限重命名
	 * 
	 * @param id
	 * @param newName
	 */
	void rename(String id, String newName);

	/**
	 * 删除权限
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 根据用户id查询权限
	 * 
	 * @param userId
	 * @return
	 */
	List<Permission> getByUserId(String userId);

	List<Permission> queryByUserIdCatHelp(String userId);
	
	/**
	 * 根据租户id查询权限
	 * 
	 * @param tenantId
	 * @return
	 */
	//List<Permission> getByTenantId(String tenantId);
	List<Permission> getByOrganId(String organId);

	/**
	 * 根据code 和用户id返回权限，若该用户无此权限返回null
	 * 
	 * @param code
	 * @param userId
	 * @return
	 */
	Permission getByCode(String code, String userId);

	/**
	 * 根据code 和租户id返回权限，若该用户无此权限返回null
	 * 
	 * @param code
	 * @param tenantId
	 * @return
	 */
	//Permission getByCodeAndTenantId(String code, String tenantId);
	Permission getByCodeAndOrganId(String code, String organId);

	/**
	 * 根据parentId 和用户id返回权限，若该用户无此权限返回null
	 * 
	 * @param parentId
	 * @param userId
	 * @return
	 */
	Permission getByParentId(String parentId, String userId);

	/**
	 * 查询模块下可用的所有权限
	 * 
	 * @return
	 */
	List<Permission> getAllModulePermissions();

	/**
	 * 查询该模块拥有的权限
	 * 
	 * @param id
	 * @return
	 */
	List<Permission> getPermissionsByModuleId(String id);

	/**
	 * 根据parentId 和租户id返回权限，若该用户无此权限返回null
	 * 
	 * @param id
	 * @param tenantId
	 * @return
	 */
	//Permission getByParentIdAndTenantId(String id, String tenantId);
	Permission getByParentIdAndOrganId(String id, String organId);

	List<Permission> getPermissionByOrganId(String organId);

	List<Permission> getAllPermissionsWithOutBase();

	Permission checkNameExists(Permission permission);

}
