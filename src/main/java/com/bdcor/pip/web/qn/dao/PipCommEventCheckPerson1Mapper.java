package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckPerson1;
import com.bdcor.pip.web.qn.domain.PipCommEventCheckPerson1Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommEventCheckPerson1Mapper {
    int countByExample(PipCommEventCheckPerson1Example example);

    int deleteByExample(PipCommEventCheckPerson1Example example);

    int deleteByPrimaryKey(Short id);

    int insert(PipCommEventCheckPerson1 record);

    int insertSelective(PipCommEventCheckPerson1 record);

    List<PipCommEventCheckPerson1> selectByExample(PipCommEventCheckPerson1Example example);

    PipCommEventCheckPerson1 selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") PipCommEventCheckPerson1 record, @Param("example") PipCommEventCheckPerson1Example example);

    int updateByExample(@Param("record") PipCommEventCheckPerson1 record, @Param("example") PipCommEventCheckPerson1Example example);

    int updateByPrimaryKeySelective(PipCommEventCheckPerson1 record);

    int updateByPrimaryKey(PipCommEventCheckPerson1 record);
}