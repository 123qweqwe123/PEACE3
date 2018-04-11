package com.bdcor.pip.web.data.service;

import java.util.List;

import com.bdcor.pip.web.data.domain.PipSysNews;
import com.bdcor.pip.web.data.domain.PipSysNewsExample;
import com.bdcor.pip.web.data.filter.NewsFilter;
import com.bdcor.pip.web.pro.promgt.domain.AdjunctType;


public interface PipSysNewsService {
	
	
	public PipSysNews queryNewsById(String id);
	
	public List<PipSysNews> queryNews(String channel);
	
	public List<PipSysNews> queryNewsByOrder(NewsFilter newsfilter);
	
	public void insertSelective(PipSysNews record);
	
	public void deleteNews(Long id);
	
	public List<PipSysNews> selectNews(PipSysNewsExample e);
}
 