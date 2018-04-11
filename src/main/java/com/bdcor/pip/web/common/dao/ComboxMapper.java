package com.bdcor.pip.web.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;

@MyBatisRepository
public interface ComboxMapper {

	List<Map<String, String>> getDict_PUB(Map<String, Object> paramMap);

	List<Map<String, String>> getDict_DISTRICT(Map<String, Object> paramMap);

	String getName_PUB(Map<String, Object> paramMap);

	String getName_DISTRICT(Map<String, Object> paramMap);
	/**
	 * 
	 * description:  通过code查找name
	 * @author yangfeng  
	 * @param vcode
	 * @return   
	 * @update 2016年4月27日
	 */
	String getAreaName(@Param("vCode")String vcode);

	List<Map<String, String>> getDict_ICD(Map<String, Object> paramMap);

	String getName_ICD(Map<String, Object> paramMap);
	
	String getICDNameById(String id);

	List<Map<String, String>> getPatientByName(@Param("q") String q , @Param("limit") String limit);

	List<Map<String, String>> getUserByName(@Param("q") String q ,
											@Param("limit") String limit,
											@Param("lccCode") String lccCode);
}