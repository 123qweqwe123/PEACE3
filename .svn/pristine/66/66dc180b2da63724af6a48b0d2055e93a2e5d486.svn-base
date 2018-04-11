package com.bdcor.pip.web.sys.rbac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.web.sys.rbac.domain.CustomFunction;

import com.bdcor.pip.core.persistence.MyBatisRepository;
//import com.genertech.adp.web.sys.rbac.domain.CustomFunction;

@MyBatisRepository
public interface CustomFunctionDao{


	public CustomFunction findById(@Param("id") String id);

	public CustomFunction findByUserAndCode(@Param("userId")String userId, @Param("code")String code);
	
	public  List<CustomFunction>  findByUerId(@Param("userId") String userId);

	public CustomFunction findByCode(@Param("code") String code);
    
    void deleteById(String id);
	
    public Integer insertOne(CustomFunction vo);
    
    public Integer updateOne(CustomFunction vo);   
    
    
    
    

}
