package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommDictIcd;
import com.bdcor.pip.web.data.domain.PipCommDictIcdExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommDictIcdMapper {
    int countByExample(PipCommDictIcdExample example);

    int deleteByExample(PipCommDictIcdExample example);

    int insert(PipCommDictIcd record);

    int insertSelective(PipCommDictIcd record);

    List<PipCommDictIcd> selectByExample(PipCommDictIcdExample example);

    int updateByExampleSelective(@Param("record") PipCommDictIcd record, @Param("example") PipCommDictIcdExample example);

    int updateByExample(@Param("record") PipCommDictIcd record, @Param("example") PipCommDictIcdExample example);
}