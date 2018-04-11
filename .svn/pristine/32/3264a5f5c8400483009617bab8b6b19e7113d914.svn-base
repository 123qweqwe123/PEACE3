package com.bdcor.pip.web.sys.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.rbac.domain.Menu;

@MyBatisRepository
public interface MenuDao {

	List<Menu> queryAllMenus();
	
	void save(Menu menu);
	
	void update(Menu menu);
	
	void delete(String id);

	 public List getMenuByUserPermission(@Param("userId") String userId);

	List getMenuById(String id);

	void updateMenuOder(@Param("id") String id, @Param("menuOrder") String menuOrder);

	Integer getOrderCount(@Param("parentId") String parentId);

	Menu checkNameExists(Menu menu);  
	
}
