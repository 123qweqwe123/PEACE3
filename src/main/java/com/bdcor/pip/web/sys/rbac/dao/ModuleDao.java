/**
 * ModuleDao.java Create on 2013-7-2
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.rbac.domain.Module;
import com.bdcor.pip.web.sys.rbac.domain.ModulePermissionDTO;
import com.bdcor.pip.web.sys.rbac.filter.ModuleFilter;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@MyBatisRepository
public interface ModuleDao {

	public void save(Module module);
	
	List<Module> queryAll();
	
	public void update(Module module);
	
	public void delete(String id);
	
	public Module queryById(String id);

	public List<Module> queryByFilter(ModuleFilter filter);
	
	public Integer queryMaxIndex();
	
	public void deleteByIds(String[] ids);

    public List<Module> queryByOrganId(String OrganId);   

	public void deleteModulePermission(String moduleId);

	public void saveModulePermissions(List<ModulePermissionDTO> mpList);
	
	long checkExists(@Param("name")String name,@Param("id")String id);
}
