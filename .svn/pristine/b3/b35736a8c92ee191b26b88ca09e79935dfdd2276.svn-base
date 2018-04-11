package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerDrop;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerDropExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipUqsAnswerDropMapper {
    int countByExample(PipUqsAnswerDropExample example);

    int deleteByExample(PipUqsAnswerDropExample example);

    int insert(PipUqsAnswerDrop record);

    int insertSelective(PipUqsAnswerDrop record);

    List<PipUqsAnswerDrop> selectByExample(PipUqsAnswerDropExample example);

    int updateByExampleSelective(@Param("record") PipUqsAnswerDrop record, @Param("example") PipUqsAnswerDropExample example);

    int updateByExample(@Param("record") PipUqsAnswerDrop record, @Param("example") PipUqsAnswerDropExample example);
}