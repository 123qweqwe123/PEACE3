/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.illness.service.impl 
 */

package com.bdcor.pip.web.illness.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.illness.dao.EventMapper;
import com.bdcor.pip.web.illness.filter.EventFilter;
import com.bdcor.pip.web.illness.service.EventService;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年3月18日         
 */
@Service
public class EventServiceImpl implements EventService
{
	@Autowired
	private EventMapper eventMapper;
	@Override
	public List<Map<String, String>> getHisList()
	{
		return eventMapper.getHisList();
	}
	@Override
	public List<Map<String, Object>> list(EventFilter filter)
	{
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return eventMapper.list(filter);
	}

}

