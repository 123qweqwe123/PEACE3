package com.bdcor.pip.web.msg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.web.msg.dao.PatientGroupDao;
import com.bdcor.pip.web.msg.service.RandomGroupService;

@Service
public class RandomGroupServiceImpl implements RandomGroupService {

	@Autowired
	private PatientGroupDao patientGroupDao;
	
	@Override
	public List<Map<String, Object>> getRandomGroup() {
		return patientGroupDao.getRandomGroup();
	}

	@Override
	public List<Map<String, Object>> getEverydayAddNum() {
		return patientGroupDao.getEverydayAddNum();
	}

}
