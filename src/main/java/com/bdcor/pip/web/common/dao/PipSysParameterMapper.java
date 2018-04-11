package com.bdcor.pip.web.common.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.common.domain.PipSysParameter;
import com.bdcor.pip.web.common.domain.PipSysParameterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipSysParameterMapper {
    int countByExample(PipSysParameterExample example);

    int deleteByExample(PipSysParameterExample example);

    int deleteByPrimaryKey(Integer parameterId);

    int insert(PipSysParameter record);

    int insertSelective(PipSysParameter record);

    List<PipSysParameter> selectByExample(PipSysParameterExample example);

    PipSysParameter selectByPrimaryKey(Integer parameterId);

    int updateByExampleSelective(@Param("record") PipSysParameter record, @Param("example") PipSysParameterExample example);

    int updateByExample(@Param("record") PipSysParameter record, @Param("example") PipSysParameterExample example);

    int updateByPrimaryKeySelective(PipSysParameter record);

    int updateByPrimaryKey(PipSysParameter record);
}