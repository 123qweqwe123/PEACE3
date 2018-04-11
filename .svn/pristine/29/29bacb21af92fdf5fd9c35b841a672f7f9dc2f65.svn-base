package com.bdcor.pip.web.spem.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.spem.domain.CheckInListVo;
import com.bdcor.pip.web.spem.filter.CheckInListFilter;


public interface CheckInListService {

	List<CheckInListVo> listQuery(CheckInListFilter filter);

	List<Map<String,Object>> getCanSelectBoxList(Map<String, String> selectBoxParamMap);

	List<Map<String, Object>> previewBoxList(Map<String, Object> paramMap);

	List<Map<String, Object>> previewTubeList(Map<String, Object> paramMap);

	int save(CheckInListVo checkInList);

	CheckInListVo getCheckInListVoById(String id);

	List<Map<String,Object>> getBoxBycheckInListId(String id);

	int modify(CheckInListVo checkInList);

	List<Map<String,Object>> getBoxByBoxIds(String[] split);

}
