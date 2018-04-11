package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckPerson;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckPersonExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommEventCheckPersonMapper {
    int countByExample(PipCommEventCheckPersonExample example);

    int deleteByExample(PipCommEventCheckPersonExample example);

    int deleteByPrimaryKey(Short id);

    int insert(PipCommEventCheckPerson record);

    int insertSelective(PipCommEventCheckPerson record);

    List<PipCommEventCheckPerson> selectByExample(PipCommEventCheckPersonExample example);

    PipCommEventCheckPerson selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") PipCommEventCheckPerson record, @Param("example") PipCommEventCheckPersonExample example);

    int updateByExample(@Param("record") PipCommEventCheckPerson record, @Param("example") PipCommEventCheckPersonExample example);

    int updateByPrimaryKeySelective(PipCommEventCheckPerson record);

    int updateByPrimaryKey(PipCommEventCheckPerson record);
}