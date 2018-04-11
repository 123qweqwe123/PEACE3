package com.bdcor.pip.web.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.web.data.dao.NewsLccMapper;
import com.bdcor.pip.web.data.dao.PipSysNewsMarqueeMapper;
import com.bdcor.pip.web.data.domain.NewsLcc;
import com.bdcor.pip.web.data.domain.NewsLccExample;
import com.bdcor.pip.web.data.domain.PipSysNewsMarquee;
import com.bdcor.pip.web.data.domain.PipSysNewsMarqueeExample;
import com.bdcor.pip.web.data.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	NewsLccMapper newsLccDao;
	@Autowired
	PipSysNewsMarqueeMapper newsMarqueeDao;
	/**
	 * 项目参加单位
	 */
	@Override
	public List<NewsLcc> getAllNewsLcc() {
		// TODO Auto-generated method stub
		NewsLccExample nc=new NewsLccExample();
		nc.setOrderByClause("sequence");//根据sequence排序
		return newsLccDao.selectByExample(nc);
	}
	@Override
	public List<PipSysNewsMarquee> getAllNewsMarquee() {
		// TODO Auto-generated method stub
		PipSysNewsMarqueeExample example=new PipSysNewsMarqueeExample();
		return newsMarqueeDao.selectByExample(example);
	}

}
