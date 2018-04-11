/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.sys.version.impl 
 */

package com.bdcor.pip.web.sys.version.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.web.sys.version.dao.PipSysVersionMapper;
import com.bdcor.pip.web.sys.version.domain.PipSysVersion;
import com.bdcor.pip.web.sys.version.domain.PipSysVersionExample;
import com.bdcor.pip.web.sys.version.domain.PipSysVersionExample.Criteria;
import com.bdcor.pip.web.sys.version.filter.PipSysVersionFilter;
import com.bdcor.pip.web.sys.version.service.VersionService;


/**  
 * description:  
 * @author yangfeng 创建时间：2016年4月20日         
 */
@Service
public class VersionServiceImpl implements VersionService
{
	@Autowired
	private PipSysVersionMapper pipSysVersionMapper;
	@Override
	public List<Map<String, Object>> list(PipSysVersionFilter filter)
	{
		return pipSysVersionMapper.list(filter);
	}
	@Override
	public PipSysVersion selectVersion(String version)
	{
		return pipSysVersionMapper.selectByPrimaryKey(version);
	}
	@Override
	public void addDate(PipSysVersion version)
	{
		pipSysVersionMapper.insert(version);
	}
	@Override
	public void update(PipSysVersion version)
	{
		pipSysVersionMapper.updateByPrimaryKeySelective(version);
	}
	@Override
	public void deleteDate(String version)
	{
		pipSysVersionMapper.deleteByPrimaryKey(version);
	}

}

