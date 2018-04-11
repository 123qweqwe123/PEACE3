package com.bdcor.pip.web.common.controller;

import com.bdcor.pip.web.common.utils.CacheUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("sys/cache") 
public class CacheManagerController {

	@RequestMapping(value="cleanByName",produces="application/json")
	public  @ResponseBody ResponseEntity  CleanCacheByName(String Names){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String cacheNames[] = Names.split(",");
			CacheManager manager = CacheManager.getInstance();
			for(String name : cacheNames){
				Cache cache = manager.getCache(name);
				cache.removeAll();
			}
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	} 
	
	@RequestMapping(value="cleanAll",produces="application/json")
	public @ResponseBody ResponseEntity CleanAll(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			CacheManager manager = CacheManager.getInstance();
			manager.removalAll(); 
			result.put("success", true);
		 } catch (CacheException e) {
			result.put("success", false);
			e.printStackTrace();
			
		 } 
		
		return new ResponseEntity(result, HttpStatus.OK);
	
	}
	
	@RequestMapping(value="query",produces="application/json")
	public ModelAndView query(){
		  ModelAndView mav = new ModelAndView("sys/cache/list");
		
		    /*List list = new ArrayList();   
	
			CacheManager manager = CacheManager.create();
			
			String[] names = manager.getCacheNames();
			for(String name:names){
				Map map = new HashMap();
				map.put("name", name);
				Cache cache = manager.getCache(name);
				long sum = cache.getStatistics().getObjectCount();
				map.put("use", sum);
				long hit = cache.getStatistics().getCacheHits();
				map.put("hit", hit);
				list.add(map);
			}
			String js = JsonMapper.nonEmptyMapper().toJson(list);
			mav.addObject("data", js);*/
		return mav;
	}	
	
	@RequestMapping(value="list",produces="application/json")
	public  @ResponseBody  List list(){
		    List list = new ArrayList();   
	
			CacheManager manager = CacheManager.getInstance();
			
			String[] names = manager.getCacheNames();
			for(String name:names){
				Map map = new HashMap();
				map.put("name", name);
				Cache cache = manager.getCache(name);
				long sum = cache.getSize ();//cache.getStatistics().getObjectCount();
				map.put("use", sum);
				long hit = cache.getStatistics().getInMemoryHits();
				
				
				map.put("hit", hit);
				list.add(map);
			}
		return list;
	}

	@Autowired
	CacheUtils cacheUtils;

	@RequestMapping("/refresh")
	@ResponseBody
	public String refreshCache(String cache) {
		cacheUtils.refreshCache(cache);
		return "success";
	}
}
