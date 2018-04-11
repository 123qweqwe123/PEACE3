package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipUqsAttribute;
import com.bdcor.pip.web.data.domain.PipUqsAttributeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipUqsAttributeMapper {
    int countByExample(PipUqsAttributeExample example);

    int deleteByExample(PipUqsAttributeExample example);

    int insert(PipUqsAttribute record);

    int insertSelective(PipUqsAttribute record);

    List<PipUqsAttribute> selectByExample(PipUqsAttributeExample example);

    int updateByExampleSelective(@Param("record") PipUqsAttribute record, @Param("example") PipUqsAttributeExample example);

    int updateByExample(@Param("record") PipUqsAttribute record, @Param("example") PipUqsAttributeExample example);
}