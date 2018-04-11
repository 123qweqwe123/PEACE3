/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.service.impl 
 */

package com.bdcor.pip.web.msg.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.msg.dao.PipMsgMsgtmpMapper;
import com.bdcor.pip.web.msg.dao.PipmsgMsgtypeMapper;
import com.bdcor.pip.web.msg.domain.PipMsgMsgtmp;
import com.bdcor.pip.web.msg.domain.PipMsgMsgtmpExample;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtmpFilter;
import com.bdcor.pip.web.msg.service.PipMsgtmpService;


/**  
 * description:  短信库
 * @author yangfeng 创建时间：2016年5月9日         
 */
@Service
public class PipMsgtmpServiceImpl implements PipMsgtmpService{
	
	@Autowired
	private PipMsgMsgtmpMapper pipMsgMsgtmpMapper;
	@Autowired
	private PipmsgMsgtypeMapper pipmsgMsgtypeMapper;
	@Override
	public List<Map<String, Object>> list(PipMsgMsgtmpFilter filter)
	{
		return pipMsgMsgtmpMapper.list(filter);
	}
	@Override
	public PipMsgMsgtmp selectById(String id)
	{
		return pipMsgMsgtmpMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Map<String, Object>> getMsgType()
	{
		return pipmsgMsgtypeMapper.listAll();
	}
	@Override
	public void insertOrUpdate(PipMsgMsgtmp pipMsgMsgtmp)
	{
		if(StringUtils.isEmpty(pipMsgMsgtmp.getId()))
		{
			pipMsgMsgtmp.setId(GenerateKey.getKey(GenerateKey.PREFIX_COMMON));
			pipMsgMsgtmp.setCreateBy(Securitys.getUserId());
			pipMsgMsgtmp.setCreateDate(new Date());
			pipMsgMsgtmpMapper.insert(pipMsgMsgtmp);
		}
		else{
			pipMsgMsgtmpMapper.updateByPrimaryKeySelective(pipMsgMsgtmp);
		}
	}
	@Override
	public void delete(String id)
	{	
		pipMsgMsgtmpMapper.deleteByPrimaryKey(id);
	}
}

