package com.bdcor.pip.web.quality.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.PipExpImplementPerson;
import com.bdcor.pip.web.quality.domain.PipExpImplementPersonExample;
import com.bdcor.pip.web.quality.filter.PipExpImplementPersonFilter;

@MyBatisRepository
public interface PipExpImplementPersonMapper {
	int countByExample(PipExpImplementPersonExample example);

	int deleteByExample(PipExpImplementPersonExample example);

	int deleteByPrimaryKey(String id);

	int insert(PipExpImplementPerson record);

	int insertSelective(PipExpImplementPerson record);

	List<PipExpImplementPerson> selectByExample(
			PipExpImplementPersonExample example);

	PipExpImplementPerson selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") PipExpImplementPerson record,
			@Param("example") PipExpImplementPersonExample example);

	int updateByExample(@Param("record") PipExpImplementPerson record,
			@Param("example") PipExpImplementPersonExample example);

	int updateByPrimaryKeySelective(PipExpImplementPerson record);

	int updateByPrimaryKey(PipExpImplementPerson record);

	/**
	 * description:通过外键关联查找相关人员
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-11-11
	 */
	List<PipExpImplementPerson> selectByFilter(
			PipExpImplementPersonFilter filter);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param ids   
	 * @update 2015-11-11
	 */
	void deleteBatch(@Param("ids") String ids);
}