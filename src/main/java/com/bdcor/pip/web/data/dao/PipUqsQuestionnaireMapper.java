package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipUqsQuestionnaire;
import com.bdcor.pip.web.data.domain.PipUqsQuestionnaireExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipUqsQuestionnaireMapper {
    int countByExample(PipUqsQuestionnaireExample example);

    int deleteByExample(PipUqsQuestionnaireExample example);

    int insert(PipUqsQuestionnaire record);

    int insertSelective(PipUqsQuestionnaire record);

    List<PipUqsQuestionnaire> selectByExample(PipUqsQuestionnaireExample example);

    int updateByExampleSelective(@Param("record") PipUqsQuestionnaire record, @Param("example") PipUqsQuestionnaireExample example);

    int updateByExample(@Param("record") PipUqsQuestionnaire record, @Param("example") PipUqsQuestionnaireExample example);
}