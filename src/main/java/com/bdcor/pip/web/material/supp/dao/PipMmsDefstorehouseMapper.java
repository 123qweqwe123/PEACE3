package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouse;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouseExample;

@MyBatisRepository
public interface PipMmsDefstorehouseMapper {
	int countByExample(PipMmsDefstorehouseExample example);

	int deleteByExample(PipMmsDefstorehouseExample example);

	int deleteByPrimaryKey(Short id);

	int insert(PipMmsDefstorehouse record);

	int insertSelective(PipMmsDefstorehouse record);

	List<PipMmsDefstorehouse> selectByExample(PipMmsDefstorehouseExample example);

	PipMmsDefstorehouse selectByPrimaryKey(Short id);

	int updateByExampleSelective(@Param("record") PipMmsDefstorehouse record,
			@Param("example") PipMmsDefstorehouseExample example);

	int updateByExample(@Param("record") PipMmsDefstorehouse record,
			@Param("example") PipMmsDefstorehouseExample example);

	int updateByPrimaryKeySelective(PipMmsDefstorehouse record);

	int updateByPrimaryKey(PipMmsDefstorehouse record);

	public List<PipMmsDefstorehouse> getAllHouseList(PipMmsDefstorehouse pmd);

	public List<PipMmsDefstorehouse> getAllHouseListByLccCode(
			PipMmsDefstorehouse pmdf);

	public List<PipMmsDefstorehouse> getOneHouseListByLccCode(
			PipMmsDefstorehouse pmdf);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param userId
	 * @return
	 * @update 2015-11-2
	 */
	List<PipMmsDefstorehouse> listAndLimit(@Param("userId") String userId);

	List<PipMmsDefstorehouse> listAndLimitLcc(@Param("userId") String userId,
			@Param("lccCode") String lccCode);

	List<PipMmsDefstorehouse> getExportStockCode(
			@Param("lccCode") String lccCode);
}