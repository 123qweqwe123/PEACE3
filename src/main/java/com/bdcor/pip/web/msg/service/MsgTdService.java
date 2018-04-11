package com.bdcor.pip.web.msg.service;

import java.util.List;

import com.bdcor.pip.web.msg.domain.MsgTdVo;
import com.bdcor.pip.web.msg.filter.MsgTdFilter;


public interface MsgTdService {

	List<MsgTdVo> list(MsgTdFilter filter);

	int audits(String[] pIdArr,int type);

}
	