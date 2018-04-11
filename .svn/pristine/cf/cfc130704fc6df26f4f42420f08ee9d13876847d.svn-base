package com.bdcor.pip.web.data.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.bdcor.pip.web.data.dao.UqsHandleViewMapper;
import com.bdcor.pip.web.data.service.ProgressCacheService;
import com.bdcor.pip.web.progress.filter.ProgressFilter;

@Service
public class ProgressCacheServiceImpl implements ProgressCacheService {

	@Autowired
	private EhCacheCacheManager cacheManager;
	
	@Autowired
	private UqsHandleViewMapper uqsHandleViewMapper;
	
	@Override
	public void flushProgressCache() {
		ProgressFilter filter = new ProgressFilter();
		filter.setRows(100);
		Cache cache = this.cacheManager.getCache("dictCache");
		List<Map> m1 = this.uqsHandleViewMapper.mapForChina(filter);
		List<Map> m2 = this.uqsHandleViewMapper.mapForArea(filter);
		List<Map> m3 = this.uqsHandleViewMapper.mapForLcc(filter);
		
		cache.put("mapForChina", m1);
		cache.put("mapForArea", m2);
		cache.put("mapForLcc", m3);
		
	}

}
