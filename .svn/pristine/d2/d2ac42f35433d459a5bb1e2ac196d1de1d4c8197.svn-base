package com.bdcor.pip.web.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.web.msg.dao.PipMsgMsgtypeDao;
import com.bdcor.pip.web.msg.domain.MsgMsgtype;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtypeFilter;
import com.bdcor.pip.web.msg.service.MsgMsgtypeService;

@Service
@Transactional
public class MsgMsgtypeServiceImpl implements MsgMsgtypeService{
	
	@Autowired
	private PipMsgMsgtypeDao msgTypeDao;

	@Override
	public List<MsgMsgtype> msgTypeList(PipMsgMsgtypeFilter filter) {
		return msgTypeDao.msgTypeList(filter);
	}

	@Override
	public int addMsgType(MsgMsgtype msgType) {
		return msgTypeDao.addMsgType(msgType);
	}

	@Override
	public int updateMsgType(MsgMsgtype msgType) {
		return msgTypeDao.updateMsgType(msgType);
	}

	@Override
	public int deleteMsgType(String msgId) {
		return msgTypeDao.deleteMsgType(msgId);
	}

	@Override
	public MsgMsgtype getMsgTypeById(String msgId) {
		return msgTypeDao.getMsgTypeById(msgId);
	}

	@Override
	public MsgMsgtype getMsgtypeByCode(String typeCode) {
		return msgTypeDao.getMsgtypeByCode(typeCode);
	}

}
