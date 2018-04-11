package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipCommEventCheck;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommEventCheckMapper {
    int countByExample(PipCommEventCheckExample example);

    int deleteByExample(PipCommEventCheckExample example);

    int deleteByPrimaryKey(Short id);

    int insert(PipCommEventCheck record);

    int insertSelective(PipCommEventCheck record);

    List<PipCommEventCheck> selectByExample(PipCommEventCheckExample example);

    PipCommEventCheck selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") PipCommEventCheck record, @Param("example") PipCommEventCheckExample example);

    int updateByExample(@Param("record") PipCommEventCheck record, @Param("example") PipCommEventCheckExample example);

    int updateByPrimaryKeySelective(PipCommEventCheck record);

    int updateByPrimaryKey(PipCommEventCheck record);
}