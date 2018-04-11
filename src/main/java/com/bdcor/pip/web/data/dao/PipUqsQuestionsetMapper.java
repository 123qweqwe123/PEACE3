package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipUqsQuestionset;
import com.bdcor.pip.web.data.domain.PipUqsQuestionsetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipUqsQuestionsetMapper {
    int countByExample(PipUqsQuestionsetExample example);

    int deleteByExample(PipUqsQuestionsetExample example);

    int insert(PipUqsQuestionset record);

    int insertSelective(PipUqsQuestionset record);

    List<PipUqsQuestionset> selectByExample(PipUqsQuestionsetExample example);

    int updateByExampleSelective(@Param("record") PipUqsQuestionset record, @Param("example") PipUqsQuestionsetExample example);

    int updateByExample(@Param("record") PipUqsQuestionset record, @Param("example") PipUqsQuestionsetExample example);
}