package com.bdcor.pip.web.msg.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.PatientGroupVo;

@MyBatisRepository
public interface PatientGroupDao {

	void changeBelongGroup(PatientGroupVo vo);
	
	void insert(PatientGroupVo vo);
	
	Map<String,BigDecimal> getGroupMap(PatientGroupVo vo);

	List<Map<String, Object>> getOldSumMapList(@Param("isDiabetes")int isDiabetes);

	List<PatientGroupVo> getTestVo();
	
	List<Map<String, Object>> getRandomGroup();
	
	List<Map<String, Object>> getEverydayAddNum();
}
