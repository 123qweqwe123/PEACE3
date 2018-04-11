package com.bdcor.pip.web.msg.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.PipMsgMsgtmp;
import com.bdcor.pip.web.msg.domain.PipMsgMsgtmpExample;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtmpFilter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipMsgMsgtmpMapper {
    int countByExample(PipMsgMsgtmpExample example);

    int deleteByExample(PipMsgMsgtmpExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipMsgMsgtmp record);

    int insertSelective(PipMsgMsgtmp record);

    List<PipMsgMsgtmp> selectByExample(PipMsgMsgtmpExample example);
    

    PipMsgMsgtmp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipMsgMsgtmp record, @Param("example") PipMsgMsgtmpExample example);

    int updateByExample(@Param("record") PipMsgMsgtmp record, @Param("example") PipMsgMsgtmpExample example);

    int updateByPrimaryKeySelective(PipMsgMsgtmp record);

    int updateByPrimaryKey(PipMsgMsgtmp record);

	List<Map<String, Object>> list(PipMsgMsgtmpFilter filter);

	List<PipMsgMsgtmp> selectMsgForMap(Map<String, Object> map);

	PipMsgMsgtmp getRandomOneByType(@Param("msgTypeCode")String msgTypeCode);
}