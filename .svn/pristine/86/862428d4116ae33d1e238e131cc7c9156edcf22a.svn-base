package com.bdcor.pip.web.msg.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.MsgNoReplyVo;
import com.bdcor.pip.web.msg.filter.MsgNoReplyFilter;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface MsgNoReplyDao {

	List<MsgNoReplyVo> list(MsgNoReplyFilter filter);
	
	List<Map<String, Object>> getMapList(MsgNoReplyFilter filter);

	List<Map<String, Object>> getNoreplyList(MsgNoReplyFilter filter);

	List<Map<String, Object>> getWaitMsg(@Param("patientId") String patientId);
}
