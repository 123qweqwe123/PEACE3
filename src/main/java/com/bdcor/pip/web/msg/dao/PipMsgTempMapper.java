package com.bdcor.pip.web.msg.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.PipMsgTemp;
import com.bdcor.pip.web.msg.domain.PipMsgTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipMsgTempMapper {
    int countByExample(PipMsgTempExample example);

    int deleteByExample(PipMsgTempExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipMsgTemp record);

    int insertSelective(PipMsgTemp record);

    List<PipMsgTemp> selectByExample(PipMsgTempExample example);

    PipMsgTemp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipMsgTemp record, @Param("example") PipMsgTempExample example);

    int updateByExample(@Param("record") PipMsgTemp record, @Param("example") PipMsgTempExample example);

    int updateByPrimaryKeySelective(PipMsgTemp record);

    int updateByPrimaryKey(PipMsgTemp record);
}