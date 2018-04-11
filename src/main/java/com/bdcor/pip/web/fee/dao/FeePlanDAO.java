package com.bdcor.pip.web.fee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.fee.domain.FeePlanVo;


@MyBatisRepository
public interface FeePlanDAO {
	
	int save( FeePlanVo vo );
	
	int delete( @Param("id") String id );
	
	List<FeePlanVo> list(@Param("orgId")  String orgId, @Param("projId")  String projId);
	
	FeePlanVo getById(String id);
	 

}
