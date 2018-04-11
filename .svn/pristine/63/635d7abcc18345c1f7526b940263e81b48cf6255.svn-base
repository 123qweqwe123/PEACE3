/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.service 
 */

package com.bdcor.pip.web.msg.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.msg.domain.PipMsgMsgtmp;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtmpFilter;

/**  
 * description:  短信库
 * @author yangfeng 创建时间：2016年5月9日         
 */
public interface PipMsgtmpService
{

	List<Map<String, Object>> list(PipMsgMsgtmpFilter filter);

	PipMsgMsgtmp selectById(String id);

	List<Map<String, Object>> getMsgType();

	void insertOrUpdate(PipMsgMsgtmp pipMsgMsgtmp);

	void delete(String id);
	

}

