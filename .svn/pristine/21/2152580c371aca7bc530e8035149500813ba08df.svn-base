package com.bdcor.pip.web.sys.rbac.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.logmgt.service.LogService;
import com.bdcor.pip.web.sys.rbac.dao.PermissionDao;
import com.bdcor.pip.web.sys.rbac.domain.Permission;
import com.bdcor.pip.web.sys.rbac.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private LogService logService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#save(com.genertech
	 * .adp.web.sys.rbac.domain.Permission)
	 */
	@Override
	@CacheEvict(value = "menuCache", allEntries = true)
	public void save(Permission permission) {

		//permission.setId(Identities.uuid());
		permission.setId(GenerateKey.getKey(GenerateKey.PREFIX_PERSSION));
		Long indexNo = permissionDao.queryMaxIndexNoByParentId(permission
				.getParentId());
		if (null == indexNo) {
			Long level = permission.getGrade();
			indexNo = Math.round(Math.pow(10, level.doubleValue()));
		}
		permission.setIndexNo(indexNo);
		permission.setCreateDatetime(new Date());
		permission.setCreateUser(Securitys.getUserId());
		permission.setIsDelete(0);

		try {
			permissionDao.save(permission);
			//logService.info(Consts.SystemModel.SYS, "添加角色 ");
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "保存权限失败");
			throw new ServiceException("保存权限失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getPermissionsByRoleId(java.lang.String)
	 */
	@Override
	//@Cacheable(value = "menuCache",key="#roleId+'getPermissionsByRoleId'") 
	public List<Permission> getPermissionsByRoleId(String roleId) {

		try {
			return permissionDao.queryByRoleId(roleId);
		} catch (Exception e) {

			//logService.error(Consts.SystemModel.SYS, "根据角色id查询权限失败");
			throw new ServiceException("根据角色id查询权限失败", e);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getAllPermissions
	 * ()
	 */
	@Override
	//@Cacheable(value = "menuCache",key="'getAllPermissions'") 
	public List<Permission> getAllPermissions() {
		try {
			return permissionDao.queryAll();
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "查询权限失败");
			throw new ServiceException("查询权限失败", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#update(com.genertech
	 * .adp.web.sys.rbac.domain.Permission)
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void update(Permission permission) {
		permission.setUpdateUser(Securitys.getUserId());
		permission.setUpdateDatetime(new Date());
		try {
			permissionDao.update(permission);
			//logService.info(Consts.SystemModel.SYS, "更新权限");
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "更新权限失败");
			throw new ServiceException("更新权限失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#rename(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void rename(String id, String newName) {

		Permission permission = new Permission();

		permission.setId(id);

		permission.setName(newName);

		try {

			permissionDao.rename(permission);
			//logService.info(Consts.SystemModel.SYS, "权限重命名");
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "权限重命名失败");
			throw new ServiceException("权限重命名失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#delete(java.
	 * lang.String)
	 */
	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void delete(String id) {

		try {
			permissionDao.delete(id);
			//logService.warning(Consts.SystemModel.SYS, "删除权限");
		} catch (Exception e) {

			//logService.error(Consts.SystemModel.SYS, "删除权限失败");
			throw new ServiceException("删除权限失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByUserId(
	 * java.lang.String)
	 */
	@Override
	//@Cacheable(value = "menuCache",key="#userId+'getPermissionByUserId'") 
	public List<Permission> getByUserId(String userId) {
		try {
			return permissionDao.queryByUserId(userId);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "根据用户id查询权限失败");
			throw new ServiceException("根据用户id查询权限失败", e);
		}
	}

	@Override
	public List<Permission> queryByUserIdCatHelp(String userId) {
		try {
			return permissionDao.queryByUserIdCatHelp(userId);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "根据用户id查询权限失败");
			throw new ServiceException("根据用户id查询权限失败", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByTenantId
	 * (java.lang.String)
	 */
	@Override
	//@Cacheable(value = "menuCache",key="#organId+'getPermByOrganId'") 
	public List<Permission> getByOrganId(String organId) {
		try {
			return permissionDao.queryByOrganId(organId);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "根据租户id查询权限失败");
			throw new ServiceException("根据租户id查询权限失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByCode(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public Permission getByCode(String code, String userId) {
		try {
			return permissionDao.querByCodeAndUserId(code, userId);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "权限获取失败");
			throw new ServiceException("权限获取失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByParentId
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public Permission getByParentId(String parentId, String userId) {
		try {
			return permissionDao.querByParentIdAndUserId(parentId, userId);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "权限获取失败");
			throw new ServiceException("权限获取失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getAllModulePermissions()
	 */
	@Override
	public List<Permission> getAllModulePermissions() {
		try {
			return permissionDao.queryAllModulePermissions();
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "查询模块可用的所有权限失败");
			throw new ServiceException("查询模块可用的所有权限失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getPermissionsByModuleId(java.lang.String)
	 */
	@Override
	public List<Permission> getPermissionsByModuleId(String id) {
		try {
			return permissionDao.queryPermissionsByModuleId(id);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "查询模块可用的所有权限失败");
			throw new ServiceException("查询模块可用的所有权限失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bdcor.pip.web.sys.rbac.service.PermissionService#getByCodeAndTenantId
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public Permission getByCodeAndOrganId(String code, String organId) {
		try {
			return permissionDao.queryByCodeAndOrganId(code, organId);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "权限获取失败");
			throw new ServiceException("权限获取失败", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bdcor.pip.web.sys.rbac.service.PermissionService#
	 * getByParentIdAndTenantId(java.lang.String, java.lang.String)
	 */
	@Override
	public Permission getByParentIdAndOrganId(String parentId, String organId) {
		try {
			Integer permissionType = 0;
			if (Securitys.isAdmin()) {
				permissionType = 1;
			}
			return permissionDao.querByParentIdAndOrganId(parentId, organId,
					permissionType);
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "权限获取失败");
			throw new ServiceException("权限获取失败", e);
		}
	}

	@Override
	public List<Permission> getAllPermissionsForMenu() {
		try {
			return permissionDao.queryAllForMenu();
		} catch (Exception e) {
			throw new ServiceException("查询权限失败", e);
		}
	}

	@Override
	public List<Permission> getPermissionByOrganId(String organId) {
		try {
			return permissionDao.getPermissionByOrganId(organId);
		} catch (Exception e) {
			throw new ServiceException("根据租户id查询权限失败", e);
		}
	}

	@Override
	public List<Permission> getAllPermissionsWithOutBase() {
		try {
			return permissionDao.queryAllWithOutBase();
		} catch (Exception e) {
			//logService.error(Consts.SystemModel.SYS, "查询权限失败");
			throw new ServiceException("查询权限失败", e);
		}
	}

	@Override
	public Permission checkNameExists(Permission permission) {
		return permissionDao.checkNameExists(permission);
	}



}
