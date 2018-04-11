package com.bdcor.pip.web.scm.dao;

import java.util.List;
import java.util.Map;
import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.scm.filter.ScmFilter;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface ScmMapper {
	
	Map<String, String> getBoxMap(Map<String, String> paramMap);

	List<Map<String, String>> getTubeList(Map<String, String> paramMap);

	void insertBox(Map<String, Object> boxMap);

	void updateBox(Map<String, Object> boxMap);

	void clearTube(Map<String, Object> boxMap);

	void insertTube(Map<String, Object> tubeMap);

	List<Map<String, Object>> boxList(ScmFilter filter);
	List<Map<String, Object>> boxListSum(ScmFilter filter);

	// 校验冻存管号是否有效
	int checkTubeCode(@Param("lcc")String lcc_code ,
					  @Param("code")String tubu_cdoe,
					  @Param("boxcode") String box_Code);
}