package com.bdcor.pip.web.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.msg.dao.MsgTdDao;
import com.bdcor.pip.web.msg.domain.MsgTdVo;
import com.bdcor.pip.web.msg.filter.MsgTdFilter;
import com.bdcor.pip.web.msg.service.MsgTdService;

@Service
public class MsgTdServiceImpl implements MsgTdService {
	
	@Autowired
	private MsgTdDao msgTdDao;

	@Override
	public List<MsgTdVo> list(MsgTdFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return msgTdDao.list(filter);
	}

	@Override
	public int audits(String[] pIdArr,int type) {
		// TODO Auto-generated method stub
		return msgTdDao.audits(pIdArr,type);
	}

}
