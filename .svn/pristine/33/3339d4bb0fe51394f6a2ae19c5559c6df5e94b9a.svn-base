package com.bdcor.pip.web.quality.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.PipExpImplement;
import com.bdcor.pip.web.quality.domain.PipExpImplementExample;
import com.bdcor.pip.web.quality.filter.PipExpImplementFilter;

@MyBatisRepository
public interface PipExpImplementMapper {
	int countByExample(PipExpImplementExample example);

	int deleteByExample(PipExpImplementExample example);

	int deleteByPrimaryKey(String id);

	int insert(PipExpImplement record);

	int insertSelective(PipExpImplement record);

	List<PipExpImplement> selectByExample(PipExpImplementExample example);

	PipExpImplement selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") PipExpImplement record,
	        @Param("example") PipExpImplementExample example);

	int updateByExample(@Param("record") PipExpImplement record, @Param("example") PipExpImplementExample example);

	int updateByPrimaryKeySelective(PipExpImplement record);

	int updateByPrimaryKey(PipExpImplement record);

	List<PipExpImplement> listByFilter(PipExpImplementFilter filter);
}