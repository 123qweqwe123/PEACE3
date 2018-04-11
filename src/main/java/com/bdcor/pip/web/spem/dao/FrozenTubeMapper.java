package com.bdcor.pip.web.spem.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.FrozenTubeVo;
import com.bdcor.pip.web.spem.filter.FrozenTubeFilter;

@MyBatisRepository
public interface FrozenTubeMapper {

	List<FrozenTubeVo> spTubeListByfilter(FrozenTubeFilter filter); 
   
}