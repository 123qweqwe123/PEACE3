/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.sys.version.service 
 */

package com.bdcor.pip.web.sys.version.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.sys.version.domain.PipSysVersion;
import com.bdcor.pip.web.sys.version.filter.PipSysVersionFilter;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年4月20日         
 */
public interface VersionService
{

	List<Map<String, Object>> list(PipSysVersionFilter filter);

	PipSysVersion selectVersion(String version);

	void addDate(PipSysVersion version);
	void update(PipSysVersion version);

	void deleteDate(String version);
}

