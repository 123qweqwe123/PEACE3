package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;

@MyBatisRepository
public interface LccDao {

	List<LccFilter> getAllLccs(LccFilter filter);

	String getLccCode(@Param("areaCode") String areaCode);

	Lcc getLcc(@Param("lccCode") String lccCode,
			@Param("projectId") String projectId);

	List<Lcc> getOneForLccCodeAndProjectId(@Param("lccCode") String lccCode,
			@Param("projectId") String projectId);

	void addLcc(Lcc lcc);

	void updateLcc(Lcc lcc);

	void updateStatus(@Param("projectId") String projectId,
			@Param("lccCode") String lccCode, @Param("status") String status);

	void delete(@Param("lccCode") String lccCode,
			@Param("projectId") String projectId);

	List<Lcc> getAllValidLccs(@Param("lccCode") String lccCode,
			@Param("projectId") String projectId);

	public List getWorkload(@Param("projectId") String projectId,
			@Param("mt") String mt);

	List<Lcc> getLccByOrganType(String[] orgType);

	List<Lcc> getAllActiveLcc(@Param("projectId") String projectId);

	List<Lcc> getDataLimitLcc(@Param("projectId") String projectId,
			@Param("userId") String userId);

	List<Lcc> getDataLimitLccByFlag(@Param("projectId") String projectId,
			@Param("userId") String userId, @Param("flag") String flag);

	List<Lcc> getDataLimitLccForLccCode(@Param("projectId") String projectId,
			@Param("userId") String userId, @Param("lccCode") String lccCode);

	void updateStatusForLcc(@Param("projectId") String projectId,
			@Param("status") String status);

	List<Map<String, String>> getDataLimitLccList(Map<String, String> map);
	List<Map<String, String>> getLccListByProvinceCode(Map<String, String> map);
	List<Map<String, String>> getProvinceList(Map<String, String> map);

	List<Map<String, String>> getRccList(Map<String, String> map);

	List<Lcc> getLccList(@Param("projectId")String projectId);

	List<Lcc> getAllActiveLccByAuthority(@Param("projectId")String current_projectId, @Param("userId")String userId);

}
