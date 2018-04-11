package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmmasterExample;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmmasterFilter;

@MyBatisRepository
public interface PipMmsImscmmasterMapper {
	int countByExample(PipMmsImscmmasterExample example);

	int deleteByExample(PipMmsImscmmasterExample example);

	int deleteByPrimaryKey(Short id);

	int insert(PipMmsImscmmaster record);
	
	int insertIfExit(PipMmsImscmmaster pmi);

	int insertSelective(PipMmsImscmmaster record);

	List<PipMmsImscmmaster> selectByExample(PipMmsImscmmasterExample example);

	PipMmsImscmmaster selectByPrimaryKey(Short id);

	int updateByExampleSelective(@Param("record") PipMmsImscmmaster record,
			@Param("example") PipMmsImscmmasterExample example);

	int updateByExample(@Param("record") PipMmsImscmmaster record,
			@Param("example") PipMmsImscmmasterExample example);

	int updateByPrimaryKeySelective(PipMmsImscmmaster record);

	int updateByPrimaryKey(PipMmsImscmmaster record);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-10-27
	 */
	List<PipMmsImscmmaster> selectByFilter(PipMmsImscmmasterFilter filter);

}