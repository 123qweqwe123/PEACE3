package com.bdcor.pip.web.fee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.fee.domain.FeeRegister;
import com.bdcor.pip.web.fee.domain.ProjectCost;
import com.bdcor.pip.web.fee.filter.FeeFilter;  
import com.bdcor.pip.web.fee.filter.FeeRegFilter;



@MyBatisRepository
public interface FeeManagerDao {
	
	public Map getLccFee(FeeFilter filter);
	
	
	public List<ProjectCost> getFeeList(FeeFilter filter);  
	
	public List<FeeRegister> getFeeRegList(FeeRegFilter filter); 
	
	int save(FeeRegister fr);

	FeeRegister getFeeRegisterById(String id);

	int delete(@Param("id")String id);

	int update(FeeRegister fr);
	
	List getFeeLineByDepart(Map map);
	
	List getFeeLineByCatagory(Map map);
	
	FeeRegister getById(@Param("id")String id);   
	
}  
