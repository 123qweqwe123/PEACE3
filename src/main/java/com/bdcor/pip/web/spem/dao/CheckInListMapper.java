package com.bdcor.pip.web.spem.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.CheckInListVo;
import com.bdcor.pip.web.spem.filter.CheckInListFilter;

@MyBatisRepository
public interface CheckInListMapper {

	List<CheckInListVo> listQuery(CheckInListFilter filter);

	List<Map<String,Object>> getCanSelectBoxList(Map<String, String> selectBoxParamMap);

	List<Map<String, Object>> previewBoxList(Map<String, Object> paramMap);

	List<Map<String, Object>> previewTubeList(Map<String, Object> paramMap);

	void updateBoxCheckInListId(Map<String, Object> map);

	int save(CheckInListVo checkInList);

	CheckInListVo getCheckInListVoById(Map<String, String> paramMap);

	void updateBoxCheckInListIdNull(String id);

	int modify(CheckInListVo checkInList);

	List<Map<String, Object>> getBoxs(Map<String, Object> paramMap); 
   
}