package com.bdcor.pip.web.sys.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.rbac.domain.Role;
import com.bdcor.pip.web.sys.rbac.domain.RolePermissionDTO;
import com.bdcor.pip.web.sys.rbac.filter.RoleFilter;

@MyBatisRepository
public interface RoleDao {

	public void save(Role role);

	public Role queryById(@Param("id") String id,
			@Param("organId") String organId);

	List<Role> queryByUserId(@Param("userId") String userId);

	List<Role> queryByUserIdAndOrganId(@Param("userId") String userId,
			@Param("organId") String organId);

	List<Role> queryAll();

	List<Role> queryAllByOrganId(@Param("organId") String organId,@Param("deptLevel") Integer deptLevel);

	List<Role> queryByFilter(RoleFilter filter);

	void update(Role role);

	void delete(String id);

	void deleteByIds(String[] ids);

	Integer queryMaxIndexNo(String organId);

	public void saveRolePermission(List<RolePermissionDTO> rpList);

	public void deleteRolePermission(String roleId);

	Long queryUserRoleCount(String[] ids);

	void deleteRolePermissions(String[] ids);

	long checkExists(@Param("name") String name, @Param("id") String id,
			@Param("organId") String organId);

	void addRoleTail(String value);

	void deleteByRoleId(String id);

	
}
