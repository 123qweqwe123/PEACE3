package com.bdcor.pip.web.qn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.qn.dao.TimeStatisticDao;
import com.bdcor.pip.web.qn.filter.TimeStatisticFilter;
import com.bdcor.pip.web.qn.service.TimeStatisticService;

@Service
public class TimeStatisticServiceImpl implements TimeStatisticService{
	
	@Autowired
	private TimeStatisticDao timeStatisticDao;

	@Override
	public List<Map<String,Object>> list(TimeStatisticFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return timeStatisticDao.list(filter);
	}

	
}
