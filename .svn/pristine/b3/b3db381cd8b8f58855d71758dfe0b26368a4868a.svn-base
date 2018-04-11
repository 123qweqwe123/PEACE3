package com.bdcor.pip.web.sys.logmgt.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.logmgt.domain.PipLogLogin;
import com.bdcor.pip.web.sys.logmgt.domain.PipLogLoginExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipLogLoginMapper {
    int countByExample(PipLogLoginExample example);

    int deleteByExample(PipLogLoginExample example);

    int insert(PipLogLogin record);

    int insertSelective(PipLogLogin record);

    List<PipLogLogin> selectByExample(PipLogLoginExample example);

    int updateByExampleSelective(@Param("record") PipLogLogin record, @Param("example") PipLogLoginExample example);

    int updateByExample(@Param("record") PipLogLogin record, @Param("example") PipLogLoginExample example);
}