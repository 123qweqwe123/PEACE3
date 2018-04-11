package com.bdcor.pip.web.qn.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.BloodTestPatientVo;
import com.bdcor.pip.web.qn.filter.BloodTestFilter;


@MyBatisRepository
public interface BloodTestDao {
	
	List<BloodTestPatientVo> getEventPatientList(BloodTestFilter filter);

	List<Map<String, Object>> showBloodTestList(BloodTestFilter filter);

}
