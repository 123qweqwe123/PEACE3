package com.bdcor.pip.web.msg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.msg.dao.MsgNoReplyDao;
import com.bdcor.pip.web.msg.domain.MsgNoReplyVo;
import com.bdcor.pip.web.msg.filter.MsgNoReplyFilter;
import com.bdcor.pip.web.msg.service.MsgNoReplyService;

@Service
public class MsgNoReplyServiceImpl implements MsgNoReplyService {
	
	@Autowired
	private MsgNoReplyDao msgNoReplyDao;
	
	@Override
	public List<MsgNoReplyVo> list(MsgNoReplyFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return msgNoReplyDao.list(filter);
	}

	@Override
	public List<Map<String, Object>> getMaplist(MsgNoReplyFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return msgNoReplyDao.getMapList(filter);
	}

	public List<Map<String, Object>> getNoreplyList(MsgNoReplyFilter filter) {
		return msgNoReplyDao.getNoreplyList(filter);
	}
}
