package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipUqsQuestion;
import com.bdcor.pip.web.data.domain.PipUqsQuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipUqsQuestionMapper {
    int countByExample(PipUqsQuestionExample example);

    int deleteByExample(PipUqsQuestionExample example);

    int insert(PipUqsQuestion record);

    int insertSelective(PipUqsQuestion record);

    List<PipUqsQuestion> selectByExample(PipUqsQuestionExample example);

    int updateByExampleSelective(@Param("record") PipUqsQuestion record, @Param("example") PipUqsQuestionExample example);

    int updateByExample(@Param("record") PipUqsQuestion record, @Param("example") PipUqsQuestionExample example);
}