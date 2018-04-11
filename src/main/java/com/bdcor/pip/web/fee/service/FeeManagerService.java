package com.bdcor.pip.web.fee.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.fee.domain.FeeCategoryStatisVo;
import com.bdcor.pip.web.fee.domain.FeeDepartStatisVo;
import com.bdcor.pip.web.fee.domain.FeeRegister;
import com.bdcor.pip.web.fee.filter.FeeFilter;
import com.bdcor.pip.web.fee.filter.FeeRegFilter;

public interface FeeManagerService {
	
	
	//public List getFeeList();

	Map getFeeList(FeeFilter filter); 
	
	List<FeeRegister> getFeeRegList(FeeRegFilter filter);
	
	FeeRegister getById(String id);
	
	int save(FeeRegister fr);
	
	int  update( FeeRegister fr);
	
	int delete(String id);
	
	Map  getFeeStatisticsByCategory(String dateStr); //statistics
	
	Map    getFeeStatisticsByDepart(String proId); 
	
	
}
