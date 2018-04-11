package com.bdcor.pip.web.pro.promgt.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;

public interface LccService {

	List<LccFilter> getAllLccs(LccFilter filter);

	List<Lcc> getAllActiveLcc();
	List<Lcc>getAllActiveLccByAuthority();

	String getLccCode(String areaCode);

	Lcc getLcc(String lccCode);

	/**
	 * 登录认证时获取lccName
	 * 
	 * @param lccCode
	 * @param projectId
	 * @return
	 */
	Lcc getLcc(String lccCode, String projectId);

	Lcc getOneForLccCodeAndProjectId(String lccCode, String projectId);

	void addLcc(Lcc lcc);

	void updateLcc(Lcc lcc);

	String updateStatus(String lccCode, String status);

	void delete(String lccCode);

	Boolean checkLccCodeExists(String lccCode);

	List<Lcc> getLccByOrganType(String[] orgType);

	List<Map<String, String>> getDataLimitLccList(Map<String, String> map);
	
	List<Map<String, String>> getLccListByProvinceCode(Map<String, String> map);
	List<Map<String, String>> getProvinceList(Map<String, String> map);

	List<Map<String, String>> getRccList(Map<String, String> map);

	/**
	 * description:flag ::主要是标识单位用户是lcc ,ncc ,rcc
	 * 
	 * @author yangfeng
	 * @param flag
	 * @return
	 * @update 2015-11-26
	 */
	List<Lcc> getDataLimitLcc(String flag);

	List<Lcc> getDataLimitLcc();
	List<Lcc> getLccList();
	List<Lcc> getDataLimitLccForLccCode();

}
