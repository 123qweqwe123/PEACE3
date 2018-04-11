package com.bdcor.pip.web.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommPatientDate;
import com.bdcor.pip.web.data.domain.PipCommPatientDateExample;
import com.bdcor.pip.web.data.domain.PipCommPatientDateKey;

@MyBatisRepository
public interface PipCommPatientDateMapper {
	int countByExample(PipCommPatientDateExample example);

	int deleteByExample(PipCommPatientDateExample example);

	int deleteByPrimaryKey(PipCommPatientDateKey key);

	int insert(PipCommPatientDate record);

	int insertSelective(PipCommPatientDate record);

	List<PipCommPatientDate> selectByExample(PipCommPatientDateExample example);

	PipCommPatientDate selectByPrimaryKey(PipCommPatientDateKey key);

	int updateByExampleSelective(@Param("record") PipCommPatientDate record,
			@Param("example") PipCommPatientDateExample example);

	int updateByExample(@Param("record") PipCommPatientDate record,
			@Param("example") PipCommPatientDateExample example);

	int updateByPrimaryKeySelective(PipCommPatientDate record);

	int updateByPrimaryKey(PipCommPatientDate record);
}