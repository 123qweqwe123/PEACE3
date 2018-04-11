package com.bdcor.pip.web.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.InWorkStatisticsDao;
import com.bdcor.pip.web.quality.domain.InWorkStatisticsVo;
import com.bdcor.pip.web.quality.filter.InWorkStatisticsFilter;
import com.bdcor.pip.web.quality.service.InWorkStatisticsService;

@Service
@Transactional
public class InWorkStatisticsServiceImpl implements InWorkStatisticsService {

	@Autowired
	private InWorkStatisticsDao inWorkStatisticsDao;

	@Override
	public List<InWorkStatisticsVo> list(InWorkStatisticsFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUser().getId());
		return inWorkStatisticsDao.list(filter);
	}
	

}
