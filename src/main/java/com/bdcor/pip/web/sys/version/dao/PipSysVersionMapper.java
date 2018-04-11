package com.bdcor.pip.web.sys.version.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.version.domain.PipSysVersion;
import com.bdcor.pip.web.sys.version.domain.PipSysVersionExample;
import com.bdcor.pip.web.sys.version.filter.PipSysVersionFilter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipSysVersionMapper {
	int countByExample(PipSysVersionExample example);

    int deleteByExample(PipSysVersionExample example);

    int deleteByPrimaryKey(String version);

    int insert(PipSysVersion record);

    int insertSelective(PipSysVersion record);

    List<PipSysVersion> selectByExample(PipSysVersionExample example);

    PipSysVersion selectByPrimaryKey(String version);

    int updateByExampleSelective(@Param("record") PipSysVersion record, @Param("example") PipSysVersionExample example);

    int updateByExample(@Param("record") PipSysVersion record, @Param("example") PipSysVersionExample example);

    int updateByPrimaryKeySelective(PipSysVersion record);

    int updateByPrimaryKey(PipSysVersion record);
    
	List<Map<String, Object>> list(PipSysVersionFilter filter);
}