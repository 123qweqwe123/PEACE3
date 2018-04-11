package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckEr;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckErExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommEventCheckErMapper {
    int countByExample(PipCommEventCheckErExample example);

    int deleteByExample(PipCommEventCheckErExample example);

    int deleteByPrimaryKey(Short id);

    int insert(PipCommEventCheckEr record);

    int insertSelective(PipCommEventCheckEr record);

    List<PipCommEventCheckEr> selectByExample(PipCommEventCheckErExample example);

    PipCommEventCheckEr selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") PipCommEventCheckEr record, @Param("example") PipCommEventCheckErExample example);

    int updateByExample(@Param("record") PipCommEventCheckEr record, @Param("example") PipCommEventCheckErExample example);

    int updateByPrimaryKeySelective(PipCommEventCheckEr record);

    int updateByPrimaryKey(PipCommEventCheckEr record);
}