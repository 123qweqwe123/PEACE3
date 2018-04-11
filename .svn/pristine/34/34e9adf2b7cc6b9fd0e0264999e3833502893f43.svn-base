package com.bdcor.pip.web.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommEventDictfile;
import com.bdcor.pip.web.data.domain.PipCommEventDictfileExample;
import com.bdcor.pip.web.data.filter.PipCommEventDictfileFilter;

@MyBatisRepository
public interface PipCommEventDictfileMapper {
	int countByExample(PipCommEventDictfileExample example);

	int deleteByExample(PipCommEventDictfileExample example);

	int deleteByPrimaryKey(String id);

	int insert(PipCommEventDictfile record);

	int insertSelective(PipCommEventDictfile record);

	List<PipCommEventDictfile> selectByExample(
			PipCommEventDictfileExample example);

	PipCommEventDictfile selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") PipCommEventDictfile record,
			@Param("example") PipCommEventDictfileExample example);

	int updateByExample(@Param("record") PipCommEventDictfile record,
			@Param("example") PipCommEventDictfileExample example);

	int updateByPrimaryKeySelective(PipCommEventDictfile record);

	int updateByPrimaryKey(PipCommEventDictfile record);
	/**
	 * 通过事件code查询
	 * @param filter
	 * @return
	 */
	List<PipCommEventDictfile> selectByFilter(PipCommEventDictfileFilter filter);
}