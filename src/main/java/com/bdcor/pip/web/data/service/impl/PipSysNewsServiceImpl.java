package com.bdcor.pip.web.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bdcor.pip.data.util.PoiUtil;
import com.bdcor.pip.web.data.dao.PipSysNewsMapper;
import com.bdcor.pip.web.data.domain.PipSysNews;
import com.bdcor.pip.web.data.domain.PipSysNewsExample;
import com.bdcor.pip.web.data.domain.PipSysNewsExample.Criteria;
import com.bdcor.pip.web.data.filter.NewsFilter;
import com.bdcor.pip.web.data.service.PipSysNewsService;
import com.bdcor.pip.web.pro.promgt.domain.AdjunctType;

@Service
public class PipSysNewsServiceImpl implements PipSysNewsService {

	@Override
	public void insertSelective(PipSysNews record) {
		pipSysNewsMapper.insertSelective(record);
		
	}

	@Override
	public List<PipSysNews> queryNewsByOrder(NewsFilter newsfilter) {
		// TODO Auto-generated method stub
		return pipSysNewsMapper.queryNewsByOrder(newsfilter);
	}

	@Autowired
	private PipSysNewsMapper pipSysNewsMapper;
	
	
	@Value("${news.path}")
	private String newspath;

	public String getNewspath() {
		return newspath;
	}

	public void setNewspath(String newspath) {
		this.newspath = newspath;
	}

	@Override
	public PipSysNews queryNewsById(String id) {
		PipSysNews news = pipSysNewsMapper.selectByPrimaryKey(Long.parseLong(id));
		if ( news != null ){
			news.setNewsContent(PoiUtil.word2Html(newspath , news.getFileName()));
		}
		return news;
	}

	@Override
	public List<PipSysNews> queryNews(String channel) {
		PipSysNewsExample example = new PipSysNewsExample();
		if(channel!=null){
			Criteria c=example.createCriteria();
			c.andChannelEqualTo(channel);
		}
		example.setOrderByClause(" ID desc , CREATE_DATE desc");
		
		List<PipSysNews> list = pipSysNewsMapper.selectByExample(example);
		List<PipSysNews> ret = new ArrayList<PipSysNews>();		
		
		if ( list != null && list.size() > 0 ){
			for ( int i = 0 ; i < Math.min(10, list.size()) ; i ++ ){
				PipSysNews news = list.get(i);
				/*if ( news != null ){
					news.setNewsContent(PoiUtil.word2Html(newspath , news.getFileName()));
				}*/
				ret.add(news);
			}
		}
		return ret;
	}

	@Override
	public void deleteNews(Long id) {
		this.pipSysNewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<PipSysNews> selectNews(PipSysNewsExample e) {
		return this.pipSysNewsMapper.selectByExample(e);
	}

}
