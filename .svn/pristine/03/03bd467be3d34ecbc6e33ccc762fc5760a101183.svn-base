package com.bdcor.pip.web.spem.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.spem.dao.CheckInListMapper;
import com.bdcor.pip.web.spem.domain.CheckInListVo;
import com.bdcor.pip.web.spem.filter.CheckInListFilter;
import com.bdcor.pip.web.spem.service.CheckInListService;
@Service
@Transactional
public class CheckInListServiceImpl implements CheckInListService {
	
	@Autowired
	private CheckInListMapper checkInListMapper;
	
	@Override
	public List<CheckInListVo> listQuery(CheckInListFilter filter) {
		filter.setProjectId (Securitys.getUser().getCurrent_projectId());
		filter.setUserId (Securitys.getUser().getId());
		return checkInListMapper.listQuery(filter);
	}

	@Override
	public List<Map<String,Object>> getCanSelectBoxList(Map<String, String> selectBoxParamMap) {
		selectBoxParamMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		selectBoxParamMap.put("userId", Securitys.getUser().getId());
		return checkInListMapper.getCanSelectBoxList(selectBoxParamMap);
	}

	@Override
	public List<Map<String, Object>> previewBoxList(Map<String, Object> paramMap) {
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("userId", Securitys.getUser().getId());
		return checkInListMapper.previewBoxList(paramMap);
	}

	@Override
	public List<Map<String, Object>> previewTubeList(Map<String, Object> paramMap) {
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("userId", Securitys.getUser().getId());
		return checkInListMapper.previewTubeList(paramMap);
	}

	@Override
	public int save(CheckInListVo checkInList) {
		if(checkInList == null || checkInList.getCheckInListNo()==null || checkInList.getCheckInListNo().trim().length()==0)return 0;
		String id=GenerateKey.getKey(GenerateKey.PREFIX_CHECKINLIST);
		checkInList.setId(id);
		checkInList.setCreateBy(Securitys.getUserId());
		checkInList.setProjectId(Securitys.getUser().getCurrent_projectId());
		checkInList.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		if(checkInList.getBoxs()!=null && checkInList.getBoxs().length()>0){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("boxs", checkInList.getBoxs().split(","));
			map.put("checkInListId",id);
			checkInListMapper.updateBoxCheckInListId(map);
		}
		return checkInListMapper.save(checkInList);
		
		
	}

	@Override
	public CheckInListVo getCheckInListVoById(String id) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("userId", Securitys.getUserId());
		paramMap.put("checkInListId", id);
		return checkInListMapper.getCheckInListVoById(paramMap);
	}

	@Override
	public List<Map<String, Object>> getBoxBycheckInListId(String id) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("checkInListId", id);
		return checkInListMapper.getBoxs(paramMap);
	}

	@Override
	public int modify(CheckInListVo checkInList) {
		if(checkInList == null || StringUtils.isBlank(checkInList.getId())|| checkInList.getCheckInListNo()==null || checkInList.getCheckInListNo().trim().length()==0)return 0;
		checkInListMapper.updateBoxCheckInListIdNull(checkInList.getId());
		if(checkInList.getBoxs()!=null && checkInList.getBoxs().length()>0){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("boxs", checkInList.getBoxs().split(","));
			map.put("checkInListId",checkInList.getId());
			checkInListMapper.updateBoxCheckInListId(map);
		}
		return checkInListMapper.modify(checkInList);
	}

	@Override
	public List<Map<String, Object>> getBoxByBoxIds(String[] boxs) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("boxs",boxs);
		return checkInListMapper.getBoxs(paramMap);
	}

}
