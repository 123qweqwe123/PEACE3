package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipSysNews;
import com.bdcor.pip.web.data.domain.PipSysNewsExample;
import com.bdcor.pip.web.data.filter.NewsFilter;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

@MyBatisRepository
public interface PipSysNewsMapper {
	List<PipSysNews> queryNewsByOrder(NewsFilter newsfilter);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int countByExample(PipSysNewsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int deleteByExample(PipSysNewsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int insert(PipSysNews record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int insertSelective(PipSysNews record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	List<PipSysNews> selectByExample(PipSysNewsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	PipSysNews selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") PipSysNews record,
			@Param("example") PipSysNewsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") PipSysNews record,
			@Param("example") PipSysNewsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(PipSysNews record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_SYS_NEWS
	 * @mbggenerated
	 */
	int updateByPrimaryKey(PipSysNews record);
	
	
}