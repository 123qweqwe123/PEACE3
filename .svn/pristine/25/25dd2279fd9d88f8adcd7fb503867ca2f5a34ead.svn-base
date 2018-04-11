package com.bdcor.pip.web.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.MsgTdVo;
import com.bdcor.pip.web.msg.filter.MsgTdFilter;

@MyBatisRepository
public interface MsgTdDao {

	List<MsgTdVo> list(MsgTdFilter filter);

	int audits(@Param("pIdArr")String[] pIdArr,@Param("type")int type);

}
