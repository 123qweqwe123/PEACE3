package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipUqsAnswer;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerExample;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipUqsAnswerMapper {
    int countByExample(PipUqsAnswerExample example);

    int deleteByExample(PipUqsAnswerExample example);

    int deleteByPrimaryKey(PipUqsAnswerKey key);

    int insert(PipUqsAnswer record);

    int insertSelective(PipUqsAnswer record);

    List<PipUqsAnswer> selectByExample(PipUqsAnswerExample example);

    PipUqsAnswer selectByPrimaryKey(PipUqsAnswerKey key);

    int updateByExampleSelective(@Param("record") PipUqsAnswer record, @Param("example") PipUqsAnswerExample example);

    int updateByExample(@Param("record") PipUqsAnswer record, @Param("example") PipUqsAnswerExample example);

    int updateByPrimaryKeySelective(PipUqsAnswer record);

    int updateByPrimaryKey(PipUqsAnswer record);
}