package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmdetalExample;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmmaster;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmdetalFilter;

@MyBatisRepository
public interface PipMmsImscmdetalMapper {
	int countByExample(PipMmsImscmdetalExample example);

	int deleteByExample(PipMmsImscmdetalExample example);

	int deleteByPrimaryKey(Short id);

	//入库中是否存在此箱记录
	int insertIfExit(PipMmsImscmdetal pmi);
	
	int insert(PipMmsImscmdetal record);

	int insertSelective(PipMmsImscmdetal record);

	List<PipMmsImscmdetal> selectByExample(PipMmsImscmdetalExample example);

	PipMmsImscmdetal selectByPrimaryKey(Short id);

	int updateByExampleSelective(@Param("record") PipMmsImscmdetal record,
			@Param("example") PipMmsImscmdetalExample example);

	int updateByExample(@Param("record") PipMmsImscmdetal record,
			@Param("example") PipMmsImscmdetalExample example);

	int updateByPrimaryKeySelective(PipMmsImscmdetal record);

	int updateByPrimaryKey(PipMmsImscmdetal record);

	/**
	 * description:通过条件查询
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-10-28
	 */
	List<PipMmsImscmdetal> selectByFilter(PipMmsImscmdetalFilter filter);
	
	public int selectArchivesNo(PipMmsImscmdetal pmi);
	
	
}