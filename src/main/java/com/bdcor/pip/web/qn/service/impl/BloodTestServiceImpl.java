package com.bdcor.pip.web.qn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.qn.dao.BloodTestDao;
import com.bdcor.pip.web.qn.domain.BloodTestPatientVo;
import com.bdcor.pip.web.qn.filter.BloodTestFilter;
import com.bdcor.pip.web.qn.service.BloodTestService;

@Service
public class BloodTestServiceImpl implements BloodTestService {
	
	@Autowired
	private BloodTestDao bloodTestDao;

	@Override
	public List<BloodTestPatientVo> getEventPatientList(BloodTestFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return bloodTestDao.getEventPatientList(filter);
	}

	@Override
	public List<Map<String, Object>> showBloodTestList(BloodTestFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return bloodTestDao.showBloodTestList(filter);
	}
	
	
}
