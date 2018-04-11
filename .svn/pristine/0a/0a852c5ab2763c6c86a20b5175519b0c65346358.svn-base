package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipUqsOption;
import com.bdcor.pip.web.data.domain.PipUqsOptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipUqsOptionMapper {
    int countByExample(PipUqsOptionExample example);

    int deleteByExample(PipUqsOptionExample example);

    int insert(PipUqsOption record);

    int insertSelective(PipUqsOption record);

    List<PipUqsOption> selectByExample(PipUqsOptionExample example);

    int updateByExampleSelective(@Param("record") PipUqsOption record, @Param("example") PipUqsOptionExample example);

    int updateByExample(@Param("record") PipUqsOption record, @Param("example") PipUqsOptionExample example);
}