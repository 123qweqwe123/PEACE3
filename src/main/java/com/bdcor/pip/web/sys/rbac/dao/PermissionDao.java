package com.bdcor.pip.web.sys.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.rbac.domain.Permission;

/**
 * 
 * <pre>
 * 功能说明：权限DAO
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@MyBatisRepository
public interface PermissionDao {

	public void save(Permission permission);
	
	public Permission queryById(String id);
	
	public List<Permission> queryByRoleId(String roleId);
	
	public List<Permission> queryAll();
	
	List<Permission> queryAllForMenu();
	
	public void update(Permission permission);
	
	public Long queryMaxIndexNoByParentId(String parentId);
	
	public void rename(Permission permission);
	
	public void delete(String id);
	
	List<Permission> queryByUserId(String userId);
	
	List<Permission> queryByUserIdCatHelp(String userId);
	
	//List<Permission> queryByTenantId(String tenantId);
	List<Permission> queryByOrganId(String organId);

    public Permission querByCodeAndUserId(@Param("code")String code, @Param("userId")String userId);
    
    public Permission queryByCodeAndOrganId(@Param("code")String code,@Param("organId")String organId);
    
    public Permission querByParentIdAndUserId(@Param("parentId")String parentId, @Param("userId")String userId);

    public Permission querByParentIdAndOrganId(@Param("parentId")String parentId, @Param("organId")String organId,@Param("permissionType")Integer permissionType);
	
    public List<Permission> queryAllModulePermissions();

	public List<Permission> queryPermissionsByModuleId(String id);

	public List<Permission> getPermissionByOrganId(String organId);

	public List<Permission> queryAllWithOutBase();

	public Permission checkNameExists(Permission permission);

}
