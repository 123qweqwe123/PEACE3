package com.bdcor.pip.web.msg.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.PipmsgMsgtype;
import com.bdcor.pip.web.msg.domain.PipmsgMsgtypeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipmsgMsgtypeMapper {
    int countByExample(PipmsgMsgtypeExample example);

    int deleteByExample(PipmsgMsgtypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipmsgMsgtype record);

    int insertSelective(PipmsgMsgtype record);

    List<PipmsgMsgtype> selectByExample(PipmsgMsgtypeExample example);

    PipmsgMsgtype selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipmsgMsgtype record, @Param("example") PipmsgMsgtypeExample example);

    int updateByExample(@Param("record") PipmsgMsgtype record, @Param("example") PipmsgMsgtypeExample example);

    int updateByPrimaryKeySelective(PipmsgMsgtype record);

    int updateByPrimaryKey(PipmsgMsgtype record);

	List<Map<String, Object>> listAll();
}