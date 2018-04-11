package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipUqsAnswerMin;
import com.bdcor.pip.web.data.domain.PipUqsAnswerMinExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipUqsAnswerMinMapper {
    int countByExample(PipUqsAnswerMinExample example);

    int deleteByExample(PipUqsAnswerMinExample example);

    int insert(PipUqsAnswerMin record);

    int insertSelective(PipUqsAnswerMin record);

    List<PipUqsAnswerMin> selectByExample(PipUqsAnswerMinExample example);

    int updateByExampleSelective(@Param("record") PipUqsAnswerMin record, @Param("example") PipUqsAnswerMinExample example);

    int updateByExample(@Param("record") PipUqsAnswerMin record, @Param("example") PipUqsAnswerMinExample example);
}