package com.bdcor.pip.web.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommPaitentDateremark;
import com.bdcor.pip.web.data.domain.PipCommPaitentDateremarkExample;

@MyBatisRepository
public interface PipCommPaitentDateremarkMapper {
	int countByExample(PipCommPaitentDateremarkExample example);

	int deleteByExample(PipCommPaitentDateremarkExample example);

	int deleteByPrimaryKey(String id);

	int insert(PipCommPaitentDateremark record);

	int insertSelective(PipCommPaitentDateremark record);

	List<PipCommPaitentDateremark> selectByExample(
			PipCommPaitentDateremarkExample example);

	PipCommPaitentDateremark selectByPrimaryKey(String id);

	int updateByExampleSelective(
			@Param("record") PipCommPaitentDateremark record,
			@Param("example") PipCommPaitentDateremarkExample example);

	int updateByExample(@Param("record") PipCommPaitentDateremark record,
			@Param("example") PipCommPaitentDateremarkExample example);

	int updateByPrimaryKeySelective(PipCommPaitentDateremark record);

	int updateByPrimaryKey(PipCommPaitentDateremark record);
}