package com.bdcor.pip.web.spem.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.ScmStaticVo;
import com.bdcor.pip.web.spem.filter.FrozenTubeFilter;
import com.bdcor.pip.web.spem.filter.ScmStaticFilter;

@MyBatisRepository
public interface ScmStaticMapper {

	List<ScmStaticVo> scmStatic(ScmStaticFilter filter); 
   
}