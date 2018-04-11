package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.LccUser;
import com.bdcor.pip.web.pro.promgt.filter.LccUserFilter;

@MyBatisRepository
public interface LccUserDao {

	List<LccUser> getAllLccUsers(LccUserFilter filter);

	LccUser getLccUserById(@Param("userCode") String userCode,
			@Param("projectId") String projectId,
			@Param("lccRoleType") String lccRoleType);

	void addLccUser(LccUser lccUser);

	void updateLccUser(LccUser lccUser);

	void changePipSysAccountStatus(LccUser lccUser);

	void changeStatus(LccUser lccUser);

	List<LccUser> getLccUsersByProjectId(@Param("projectId") String projectId);

	void changeStatusByLccCode(@Param("lccStatus") Integer lccStatus,
			@Param("lccCode") String lccCode);

	List<LccUser> getLccUserByLccCode(@Param("lccCode") String lccCode,
			@Param("projectId") String projectId);

	void updateStatusForLccUser(@Param("projectId") String projectId,
			@Param("status") String status);

	void updateStatusForLccCodeAndProjectId(
			@Param("projectId") String projectId,
			@Param("lccCode") String lccCode, @Param("status") String status);

	void updatePIPSYSACCOUNTStatusForLccCodeAndProjectId(
			@Param("projectId") String projectId,
			@Param("lccCode") String lccCode, @Param("status") String status);

	String getMaxLccCode(@Param("projectId") String projectId,
			@Param("lccCode") String lccCode);

	public List<LccUser> getOneForLccCodeAndProjectIdAndUserCode(
			@Param("lccCode") String lccCode,
			@Param("projectId") String projectId,
			@Param("userCode") String userCode);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param map
	 * @return
	 * @update 2015-11-10
	 */
	List<Map<String, String>> getLccUserList(Map<String, String> map);
	/**
	 * 
	 * description:  只会更新用户的账户单位 通过userCode 和projectId
	 * @author yangfeng  
	 * @param lccUser   
	 * @update 2016年5月26日
	 */
	void updateAccountLccByUserCode(LccUser lccUser);

}
