/**
 * ModuleService.java Create on 2013-7-2
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.service;

import java.util.List;

import com.bdcor.pip.web.sys.rbac.domain.Module;
import com.bdcor.pip.web.sys.rbac.filter.ModuleFilter;

/**
 * <pre>
 * 功能说明：模块管理Service
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public interface ModuleService {

	/**
	 * 保存模块
	 * @param module
	 */
	public String save(Module module);
	
	/**
	 * 查询全部模块 （is_delete !=1）
	 * @return
	 */
	public List<Module> getAll();
	
	/**
	 * 更新
	 * @param module
	 */
	public String update(Module module);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 根据filter中的条件查询
	 * @param filter
	 * @return
	 */
	public List<Module> getByFilter(ModuleFilter filter);
	
	/**
	 * 根据ID返回Module对象
	 * @param id
	 * @return
	 */
	public Module getById(String id);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteByIds(String[] ids);

	/**
	 * 根据租户id查询该租户下的所有模块
	 * @param id
	 * @return
	 */
    public List<Module> getByOrganId(String organId);

    /**
     * 保存模块权限
     * @param module
     * @param ids
     */
	public String save(Module module, String[] ids);

	/**
	 * 更新模块权限
	 * @param module
	 * @param ids
	 */
	public String update(Module module, String[] ids);

	/**
	 * 检查模块名是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	boolean checkExists(String name,String id);
}
