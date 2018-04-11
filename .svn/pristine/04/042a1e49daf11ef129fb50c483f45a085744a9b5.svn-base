package com.bdcor.pip.web.sys.rbac.service;

import java.util.List;

import com.bdcor.pip.web.sys.rbac.domain.Menu;

public interface MenuService {

	List<Menu> getAllMenus();

	void save(Menu menu);
	
	void update(Menu menu);
	
	void delete(String id);

	/**
	 * 获取所有有权限的菜单
	 * 
	 * @return
	 */
	public List getMenuByUserId(String userId);

	List getMenuById(String id);

	void updateMenuOrder(String menuOrderParam);

	Integer getOrderCount(String parentId);

	Menu checkNameExists(Menu menu);

	String menuFullName(String url);
}
