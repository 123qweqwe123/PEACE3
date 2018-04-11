package com.bdcor.pip.web.sys.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.web.sys.rbac.dao.MenuDao;
import com.bdcor.pip.web.sys.rbac.domain.Menu;
import com.bdcor.pip.web.sys.rbac.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	@Cacheable(value = "menuCache")
	public List<Menu> getAllMenus() {
		try {
			
			return  menuDao.queryAllMenus();
		} catch (Exception e) {
			throw new ServiceException("查询菜单失败", e);
		}
	}  

	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void save(Menu menu) {
		try {
			menu.setId(GenerateKey.getKey(GenerateKey.PREFIX_MENU));
			menuDao.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存菜单失败", e);
		}
	}

	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void update(Menu menu) {
		try {
			menuDao.update(menu);
		} catch (Exception e) {
			throw new ServiceException("更新菜单失败", e);
		}
	}

	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void delete(String id) {
		try {
			menuDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException("删除菜单失败", e);
		}
	}
	
	
	@Override
	@Cacheable(value = "menuCache",key="#userId")  
	public List getMenuByUserId(String userId) { 
		 
		 @SuppressWarnings("rawtypes")
		List<Map> menus = menuDao.getMenuByUserPermission(userId); 
		 List<Map> bars = new ArrayList(); 
		 
		 for(Map m:menus){
			 if((m.get("TYPE")).equals("1")){
				 bars.add(m);
			 }
		 }
		 for(Map m:bars){
			 int p=1;
			  for(Map i:menus){
				   if((i.get("PARENT") ).equals(m.get("ID"))){
					   p++;
				  }
			  }
			  if(p==1)
				  menus.remove(m);	  
		 }
		 return menus;
	}  

	@Override
	public List getMenuById(String id) {
		return menuDao.getMenuById(id==null?"":id);
	}

	@Override
	@CacheEvict(value = "menuCache",allEntries = true)
	public void updateMenuOrder(String menuOrderParam) {
		try{
			String[] str = menuOrderParam.split(";");
			for(int i=0;i<str.length;i++){
				String[] menu = str[i].split(":");
				menuDao.updateMenuOder(menu[0], menu[1]);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("更新排序失败！",e);
		}
	}

	@Override
	public Integer getOrderCount(String parentId) {
		return menuDao.getOrderCount(parentId);
	}

	@Override
	public Menu checkNameExists(Menu menu) {
		return menuDao.checkNameExists(menu);
	}

	@Override
	public String menuFullName(String url) {
		List<Menu> ms = menuDao.queryAllMenus();
		List<String> ns = new ArrayList<String>();
		for ( Menu m : ms ){
			if ( m.getTarget() != null && m.getTarget().equals(url)){
				ns.add( m.getText());
				menuName(ns , m , ms);
				break;
			}
		}
		if ( ns != null && ns.size() > 0 ){
			StringBuffer sb = new StringBuffer();
			for ( String n : ns ){
				sb.append("<li class=\"active\">");
				sb.append(n);
				sb.append("</li>");
			}
			return sb.toString();
		}
		return null;
	}
	
	private void menuName ( List<String> ns , Menu m, List<Menu> ms){
		if (m.getParent().equals("1"))
			return ;
		for ( Menu mm : ms ){
			if ( mm.getId().equals(m.getParent())){
				ns.add( 0 , mm.getText());
				menuName(ns , mm , ms);
				break;
			}
		}
	} 

}
