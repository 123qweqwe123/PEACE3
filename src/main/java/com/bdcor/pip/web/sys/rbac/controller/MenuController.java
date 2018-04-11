package com.bdcor.pip.web.sys.rbac.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.rbac.domain.Menu;
import com.bdcor.pip.web.sys.rbac.service.MenuService;


@Controller
@RequestMapping(value = "sys/rbac/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;

//	//@RequiresPermissions("menumgt:menu")
	@RequestMapping
    public String init(){
        
        return "sys/rbac/menu/list";
    }
	
	//@RequiresPermissions("menumgt:menu")
	@RequestMapping(value = "getAllMenus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Menu> getAllMenus() {
		List<Menu> listMenu = menuService.getAllMenus();
		return listMenu;
	}
	
	//@RequiresPermissions("menumgt:add")
	@RequestMapping(value="add",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean addMenu(@Valid Menu menu,Errors errors){
		if(errors.hasErrors()){
			return false;
		}
		try{
			menuService.save(menu);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//@RequiresPermissions("menumgt:delete")
	@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delete(@RequestParam(value="id",required=true)String id){
		try{
			menuService.delete(id);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//@RequiresPermissions("menumgt:update")
	@RequestMapping(value="update",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean update(@Valid  Menu menu, Errors errors){
		if(errors.hasErrors()){
			return false;
		}
		try{
			menuService.update(menu);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "menuTree", method = RequestMethod.GET)
	@ResponseBody
	public List menuTree() { 
		
		if(Securitys.isAdmin()){
			return menuService.getAllMenus(); 
		} 
		
		List menus = menuService.getMenuByUserId(Securitys.getUserId());
		
		//Securitys.getSubject().getSession()
		
		return menus;				
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "getMenuById", method = RequestMethod.POST)
	@ResponseBody
	public List getMenuById(String id){
		return menuService.getMenuById(id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "updateMenuOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> updateMenuOrder(String menuOrderParam){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			menuService.updateMenuOrder(menuOrderParam);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getOrderCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getOrderCount(String parentId){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Integer order = menuService.getOrderCount(parentId);
			result.put("order", order);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="checkNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean checkNameExists(Menu menu){
		Menu m = menuService.checkNameExists(menu);
		if(m==null){
			return false;
		}else{
			return true;
		}
	}
	
	@RequestMapping(value = "menuFullName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> menuFullName(String url){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String ret = menuService.menuFullName(url);
			result.put("content", ret);
			result.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
