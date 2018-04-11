package com.bdcor.pip.web.pro.promgt.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.pro.promgt.domain.LccUser;
import com.bdcor.pip.web.pro.promgt.filter.LccUserFilter;

public interface LccUserService {

	List<LccUser> getAllLccUsers(LccUserFilter filter);

	List<LccUser> getOneForLccCodeAndProjectIdAndUserCode(String lccCode,
			String projectId, String userCode);

	LccUser getLccUserById(String userCode, String lccRoleType);

	void addLccUser(LccUser lccUser);

	void updateLccUser(LccUser lccUser);

	void changeStatus(LccUser lccUser);

	void changePipSysAccountStatus(LccUser lccUser);

	List<LccUser> getLccUsersByProjectId();

	List<LccUser> getLccUserByLccCode(String lccCode);

	/**
	 * description: 查询单位用户信息
	 * 
	 * @author yangfeng
	 * @param map
	 * @return
	 * @update 2015-11-10
	 */
	List<Map<String, String>> getLccUserList(Map<String, String> map);

}
