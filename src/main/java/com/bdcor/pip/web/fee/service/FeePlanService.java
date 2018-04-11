package com.bdcor.pip.web.fee.service;

import java.util.List;

import com.bdcor.pip.web.fee.domain.FeePlanVo;

public interface FeePlanService {
	
	
	List<FeePlanVo> list(String organId);  
	
	int save(FeePlanVo vo);  
	
	int delete(String id); 

	FeePlanVo getById(String id);
	
	

}
