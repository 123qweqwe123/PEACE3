package com.bdcor.pip.web.qn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.qn.dao.TimeListDao;
import com.bdcor.pip.web.qn.filter.TimeListFilter;
import com.bdcor.pip.web.qn.service.TimeListService;

@Service
public class TimeListServiceImpl implements TimeListService{
	
	@Autowired
	private TimeListDao timeListDao;

	@Override
	public List<Map<String, Object>> list(TimeListFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return timeListDao.list(filter);
	}

	
}
