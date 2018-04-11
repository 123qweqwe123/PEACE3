package com.bdcor.pip.web.data.service;

import java.util.List;

import com.bdcor.pip.web.data.domain.NewsLcc;
import com.bdcor.pip.web.data.domain.PipSysNewsMarquee;

public interface NewsService {
	/**项目参加单位*/
	List<NewsLcc> getAllNewsLcc();
	/**首页滚动标题*/
	List<PipSysNewsMarquee> getAllNewsMarquee();

}
