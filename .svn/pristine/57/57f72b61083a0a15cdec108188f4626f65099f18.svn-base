package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetalExample;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmdetalFilter;

@MyBatisRepository
public interface PipMmsExscmdetalMapper {
	int countByExample(PipMmsExscmdetalExample example);

	int deleteByExample(PipMmsExscmdetalExample example);

	int deleteByPrimaryKey(Short id);

	int insert(PipMmsExscmdetal record);

	int insertSelective(PipMmsExscmdetal record);

	List<PipMmsExscmdetal> selectByExample(PipMmsExscmdetalExample example);

	PipMmsExscmdetal selectByPrimaryKey(Short id);

	int updateByExampleSelective(@Param("record") PipMmsExscmdetal record,
			@Param("example") PipMmsExscmdetalExample example);

	int updateByExample(@Param("record") PipMmsExscmdetal record,
			@Param("example") PipMmsExscmdetalExample example);

	int updateByPrimaryKeySelective(PipMmsExscmdetal record);

	int updateByPrimaryKey(PipMmsExscmdetal record);

	List<PipMmsExscmdetal> selectByFilter(PipMmsExscmdetalFilter filter);
}