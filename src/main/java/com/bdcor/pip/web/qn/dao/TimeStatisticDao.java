package com.bdcor.pip.web.qn.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.filter.TimeStatisticFilter;


@MyBatisRepository
public interface TimeStatisticDao {
	
	List<Map<String, Object>> list(TimeStatisticFilter filter);

}
