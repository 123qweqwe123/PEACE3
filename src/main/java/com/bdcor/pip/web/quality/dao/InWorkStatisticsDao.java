package com.bdcor.pip.web.quality.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.InWorkStatisticsVo;
import com.bdcor.pip.web.quality.filter.InWorkStatisticsFilter;

@MyBatisRepository
public interface InWorkStatisticsDao {
	
	List<InWorkStatisticsVo> list(InWorkStatisticsFilter filter);

}
