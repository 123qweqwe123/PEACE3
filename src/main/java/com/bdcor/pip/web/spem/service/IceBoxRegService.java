package com.bdcor.pip.web.spem.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.spem.domain.IceBoxRegVo;
import com.bdcor.pip.web.spem.filter.IceBoxRegFilter;

public interface IceBoxRegService {
	
	
	List<IceBoxRegVo> list(String lccId);  
	
	int save(IceBoxRegVo vo);  
	
	//int save(IceBoxRegVo vo);  
	
	int delete(String id,String projectId);  
	
	IceBoxRegVo getById(String id);
	
	List<IceBoxRegVo> qlist(IceBoxRegFilter fi);

	List<Map<String, String>> getFrigdeList(Map<String,Object> map);

	List<Map<String,Object>> getExportFrigdeList(Map<String, Object> paramMap); 
	

}
