package com.bdcor.pip.web.msg.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.PipMsgHandler;
import com.bdcor.pip.web.msg.domain.PipMsgHandlerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipMsgHandlerMapper {
    int countByExample(PipMsgHandlerExample example);

    int deleteByExample(PipMsgHandlerExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipMsgHandler record);

    int insertSelective(PipMsgHandler record);

    List<PipMsgHandler> selectByExample(PipMsgHandlerExample example);

    PipMsgHandler selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipMsgHandler record, @Param("example") PipMsgHandlerExample example);

    int updateByExample(@Param("record") PipMsgHandler record, @Param("example") PipMsgHandlerExample example);

    int updateByPrimaryKeySelective(PipMsgHandler record);

    int updateByPrimaryKey(PipMsgHandler record);
}